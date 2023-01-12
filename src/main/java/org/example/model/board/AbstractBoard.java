package org.example.model.board;

import org.example.model.Color;
import org.example.model.Field;
import org.example.model.rules.Rules;

import java.util.ArrayList;

public abstract class AbstractBoard {

    protected Rules rules;
    protected ArrayList<Field> fields = new ArrayList<>();
    protected Color turn = Color.WHITE;

    public Color getTurn() { return turn; }
    public ArrayList<Field> getFields() {
        return fields;
    }
    public Field getFieldAt(int x, int y) {
        return fields.stream().filter(f -> f.getRow() == x && f.getColumn() == y).findFirst().orElse(null);
    }
    public void move(int oldX, int oldY, int newX, int newY, Color color) throws illegalMoveException, GameOverException {
        Field oldField = getFieldAt(oldX, oldY);
        Field newField = getFieldAt(newX, newY);

        ArrayList <Field> capturedFields = findCapturedFields(oldField, newField);
        if(!rules.isMoveValid(oldField, newField, capturedFields, color)) {
            throw new illegalMoveException();
        }

        if(rules.isCapturingObligatory()) {
            if(isAnyCapture(color) && capturedFields.size() == 0) {
                throw new illegalMoveException();
            }
        }

        newField.setColor(oldField.getColor());
        oldField.setColor(Color.NONE);
        if( (newField.getRow() == 0 && color == Color.WHITE) ||
                (newField.getRow() == getNoRows()-1 && color == Color.BLACK ) ||
                (oldField.getIsKing())) {
            newField.setIsKing(oldField.getIsKing()); }
        oldField.setIsKing(false);

        for ( Field field: capturedFields ) {
             field.setColor(Color.NONE);
             field.setIsKing(false);
        }

        if(isGameOver(color.getOppositeColor())) {
            throw new GameOverException();
        }

        if(capturedFields.size() == 0 || !isCapturePossible(newField, color)) {
            turn = turn.getOppositeColor();
        }
    }

    protected ArrayList<Field> findCapturedFields (Field oldField, Field newField) {
        ArrayList <Field> capturedFields = new ArrayList<>();
        if(oldField == null || newField == null) { return capturedFields; }
        int i, j, maxRow, bias = 1;
        if (oldField.getRow() < newField.getRow()) {
            i = oldField.getRow()+1;
            maxRow = newField.getRow();
            j = oldField.getColumn();
            if ( j > newField.getColumn()) { bias = -1; }
        }
        else {
            i = newField.getRow()+1;
            maxRow = oldField.getRow();
            j = newField.getColumn();
            if ( j > oldField.getColumn()) { bias = -1; }
        }
        j += bias;

        while ( i < maxRow) {
            Field temp = getFieldAt(i,j);
            if (temp != null) { capturedFields.add(temp); }
            i++;
            j+=bias;
        }
        return capturedFields;
    }
    protected boolean isAnyCapture(Color color) {
        for (Field field:fields) {
            if( field.getColor() == color && isCapturePossible(field, color)) {
                return true;
            }
        }
        return false;
    }
    protected boolean isCapturePossible(Field field, Color color) {

        ArrayList<Field> possibleMoves = new ArrayList<>();
        possibleMoves.add(getFieldAt(field.getRow()+2,field.getColumn()-2));
        possibleMoves.add(getFieldAt(field.getRow()+2,field.getColumn()+2));
        possibleMoves.add(getFieldAt(field.getRow()-2,field.getColumn()-2));
        possibleMoves.add(getFieldAt(field.getRow()-2,field.getColumn()+2));

        if (field.getIsKing()) {
            int i = field.getRow()+1;
            int j = field.getColumn()-1;
            Field f = getFieldAt(i, j);
            while (f != null) {
                possibleMoves.add(f);
                i++;
                j--;
                f = getFieldAt(i, j);
            }
            i = field.getRow()+1;
            j = field.getColumn()+1;
            f = getFieldAt(i,j);
            while (f != null) {
                possibleMoves.add(f);
                i++;
                j++;
                f = getFieldAt(i, j);
            }
            i = field.getRow()-1;
            j = field.getColumn()-1;
            f = getFieldAt(i,j);
            while (f != null) {
                possibleMoves.add(f);
                i--;
                j--;
                f = getFieldAt(i, j);
            }
            i = field.getRow()-1;
            j = field.getColumn()+1;
            f = getFieldAt(i,j);
            while (f != null) {
                possibleMoves.add(f);
                i--;
                j++;
                f = getFieldAt(i, j);
            }
        }
        for (Field f:possibleMoves) {
            if(f != null && rules.isMoveValid(field, f, findCapturedFields(field, f), color)) {
                return true;
            }
        }

        return false;
    }
    protected boolean isGameOver(Color color) {
        if (fields.stream().noneMatch(f -> f.getColor() == color)) {
            return true;
        }
        return fields.stream().noneMatch(f -> f.getColor() == color && isAnotherMovePossible(f, color));
    }

    protected boolean isAnotherMovePossible(Field field, Color color) {

        if(field == null) {
            return false;
        }
        if (isCapturePossible(field, color)) {
            return true;
        }
        ArrayList<Field> possibleMoves = new ArrayList<>();
        int bias = 1;
        if (color == Color.WHITE) {
               bias = -1;
        }
        //standard moves
        possibleMoves.add(getFieldAt(field.getRow()+bias,field.getColumn()-1));
        possibleMoves.add(getFieldAt(field.getRow()+bias,field.getColumn()+1));


        if(field.getIsKing()) {
            possibleMoves.add(getFieldAt(field.getRow()-bias,field.getColumn()-1));
            possibleMoves.add(getFieldAt(field.getRow()-bias,field.getColumn()+1));
        }

        for (Field f:possibleMoves) {
            if(f != null && rules.isMoveValid(field, f, findCapturedFields(field, f), color)) {
                return true;
            }
        }
        return false;
    }

    protected abstract int getNoRows();
}