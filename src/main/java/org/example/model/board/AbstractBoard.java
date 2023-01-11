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
    public void move(int oldX, int oldY, int newX, int newY, Color color) throws illegalMoveException {
        Field oldField = getFieldAt(oldX, oldY);
        Field newField = getFieldAt(newX, newY);

        ArrayList <Field> capturedFields = findCapturedFields(oldField, newField);
        if(!rules.isMoveValid(oldField, newField, capturedFields, color)) {
            throw new illegalMoveException();
        }

        if(rules.isCapturingObligatory()) {
            ArrayList<Field> obligatoryNewFields = findObligatoryMoves(oldField, color);
            if (obligatoryNewFields.size() > 0 && !obligatoryNewFields.contains(newField)) {
                throw new illegalMoveException();
            }
        }

        newField.setColor(oldField.getColor());
        oldField.setColor(Color.NONE);
        newField.setIsKing(oldField.getIsKing());
        oldField.setIsKing(false);

        for ( Field field: capturedFields ) {
             field.setColor(Color.NONE);
             field.setIsKing(false);
        }

        if(isGameOver(color)) {
            turn = Color.NONE;
        }
        else if( capturedFields.size() == 0 || !isCapturePossible(newField, color)) {
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
    protected ArrayList<Field> findObligatoryMoves(Field oldField, Color color) {
        ArrayList<Field> possibleMoves  = new ArrayList<>();
        //TODO wyszukanie obligatoryjnych ruchow
        //wygeneruj possible moves (mozliwe) w zaleznosci od zasad i isKing
        //for each field:possibleMoves
        // if !rules.isMoveValid possibleMoves.remove(field)
                //policz maksymalna liczbe bic i dodaj te ruchy do listy
        ArrayList<Field> obligatoryMoves = new ArrayList<>();
        return obligatoryMoves;
    }
    protected boolean isCapturePossible(Field oldField, Color color) {
        //TODO sprawdzenie czy gracz ma kolejne bicie w swoim ruchu
        //sprawdz bicie na lewej i prawej przekatnej czy ktores jest valid
        //albo wszytskie inne mozliwe dla damki
        if(!oldField.getIsKing()) {
            Field field1 = fields.stream().filter(f -> f.getColumn() == oldField.getColumn() + 2 && f.getRow() == oldField.getRow() + 2).findFirst().orElse(null);
            Field captured1 = fields.stream().filter(f -> f.getColumn() == oldField.getColumn() + 1 && f.getRow() == oldField.getRow() + 1).findFirst().orElse(null);
            ArrayList<Field> capturedList = new ArrayList<>();
            capturedList.add(captured1);

            Field field2 = fields.stream().filter(f -> f.getColumn() == oldField.getColumn() - 2 && f.getRow() == oldField.getRow() + 2).findFirst().orElse(null);
            Field captured2 = fields.stream().filter(f -> f.getColumn() == oldField.getColumn() - 1 && f.getRow() == oldField.getRow() + 1).findFirst().orElse(null);
            ArrayList<Field> capturedList2 = new ArrayList<>();
            capturedList2.add(captured2);
            if (rules.isMoveValid(oldField, field1, capturedList, color) || rules.isMoveValid(oldField, field2, capturedList2, color))
                return true;
            return false;
        }

        return false;
    }
    protected boolean isGameOver(Color color) {
        if (fields.stream().noneMatch(f -> f.getColor() == color.getOppositeColor())) {
            return true;
        }
        return fields.stream().noneMatch(f -> f.getColor() == color.getOppositeColor() && isAnotherMovePossible(f, color.getOppositeColor()));
    }

    protected boolean isAnotherMovePossible(Field field, Color color) {

        if(field == null) {
            return false;
        }
        ArrayList<Field> possibleMoves = new ArrayList<>();
       if (!field.getIsKing()) {
           int bias = 1;
           if (color == Color.WHITE) {
               bias = -1;
           }
           //standard moves
           possibleMoves.add(getFieldAt(field.getRow()+bias,field.getColumn()-1));
           possibleMoves.add(getFieldAt(field.getRow()+bias,field.getColumn()+1));
           //capturing
           possibleMoves.add(getFieldAt(field.getRow()+2,field.getColumn()-2));
           possibleMoves.add(getFieldAt(field.getRow()+2,field.getColumn()+2));
           possibleMoves.add(getFieldAt(field.getRow()-2,field.getColumn()-2));
           possibleMoves.add(getFieldAt(field.getRow()-2,field.getColumn()+2));
       }
       else {
           //top left
           Field f = getFieldAt(field.getRow()+1, field.getColumn()-1);
           while(f != null) {
               possibleMoves.add(f);
               f = getFieldAt(f.getRow()+1, f.getColumn()-1);
           }
           //top right
           f = getFieldAt(field.getRow()+1, field.getColumn()+1);
           while(f != null) {
               possibleMoves.add(f);
               f = getFieldAt(f.getRow()+1, f.getColumn()+1);
           }
           //down left
           f = getFieldAt(field.getRow()-1, field.getColumn()-1);
           while(f != null) {
               possibleMoves.add(f);
               f = getFieldAt(f.getRow()-1, f.getColumn()-1);
           }
           //down right
           f = getFieldAt(field.getRow()-1, field.getColumn()+1);
           while(f != null) {
               possibleMoves.add(f);
               f = getFieldAt(f.getRow()-1, f.getColumn()+1);
           }

       }
        for (Field f:possibleMoves) {
            if(f != null && rules.isMoveValid(field, f, findCapturedFields(field, f), color)) {
                return true;
            }
        }
        return false;
    }
}