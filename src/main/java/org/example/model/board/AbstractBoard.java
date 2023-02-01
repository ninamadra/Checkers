package org.example.model.board;

import org.example.model.Color;
import org.example.model.Field;
import org.example.model.rules.Rules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * A class which contains fields on the board and rules. It performs moves "on the board" if valid
 */
public abstract class AbstractBoard {

    protected Rules rules;
    protected ArrayList<Field> fields = new ArrayList<>();
    protected Color turn = Color.WHITE;

    public Color getTurn() { return turn; }
    public ArrayList<Field> getFields() {
        return fields;
    }

    /**
     * @param x number of row
     * @param y number of column
     * @return field on a given position
     */
    public Field getFieldAt(int x, int y) {
        return fields.stream().filter(f -> f.getRow() == x && f.getColumn() == y).findFirst().orElse(null);
    }

    /**
     * Changing fields according to given move coordinates if valid
     * @param oldX number of row from which piece is being removed
     * @param oldY number of column from which piece is being removed
     * @param newX number of row of desired new piece location
     * @param newY number of column of desired new piece location
     * @param color color of player who made a move
     * @throws illegalMoveException if move is invalid
     * @throws GameOverException if game is over
     */
    public void move(int oldX, int oldY, int newX, int newY, Color color) throws illegalMoveException, GameOverException {
        Field oldField = getFieldAt(oldX, oldY);
        Field newField = getFieldAt(newX, newY);

        ArrayList <Field> capturedFields = findCapturedFields(oldField, newField);
        if(!rules.isMoveValid(oldField, newField, capturedFields, color)) {
            throw new illegalMoveException();
        }

        if(rules.isCapturingObligatory()) {
            if(isAnyCapture(color)) {
                if(capturedFields.size() == 0) {
                    throw new illegalMoveException();
                }
                if(capturedFields.stream().noneMatch(f -> f.getColor() == color.getOppositeColor())) {
                    throw new illegalMoveException();
                }
            }
        }

        newField.setColor(oldField.getColor());
        oldField.setColor(Color.NONE);
        if( (newField.getRow() == 0 && color == Color.WHITE) ||
                (newField.getRow() == getNoRows()-1 && color == Color.BLACK ) ||
                (oldField.getIsKing())) {
            newField.setIsKing(true); }

        if(isGameOver(color.getOppositeColor())) {
            throw new GameOverException();
        }
        boolean flag = false; //change turn only once and clear captured fields in between
        if(capturedFields.stream().allMatch(f -> f.getColor() == Color.NONE)) {
            turn = turn.getOppositeColor();
            flag = true;
        }
        oldField.setIsKing(false);
        for ( Field field: capturedFields ) {
            field.setColor(Color.NONE);
            field.setIsKing(false);
        }
        if(!flag && (capturedFields.size() == 0 || !isCapturePossible(newField, color))) {
            turn = turn.getOppositeColor();
        }

    }

    /**
     * @param oldField field from which the piece is being removed
     * @param newField desired location of the piece
     * @return list of fields captured on the way
     */
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

    /**
     * @param color color of pieces to be checked
     * @return true if there's a possible capture, false otherwise
     */
    protected boolean isAnyCapture(Color color) {
        for (Field field:fields) {
            if( field.getColor() == color && isCapturePossible(field, color)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param field field from which the piece is being removed
     * @return list of possible captures
     */
    protected ArrayList<Field> getPossibleCapturesList(Field field) {
        ArrayList<Field> possibleMoves = new ArrayList<>();
        possibleMoves.add(getFieldAt(field.getRow()+2,field.getColumn()-2));
        possibleMoves.add(getFieldAt(field.getRow()+2,field.getColumn()+2));
        possibleMoves.add(getFieldAt(field.getRow()-2,field.getColumn()-2));
        possibleMoves.add(getFieldAt(field.getRow()-2,field.getColumn()+2));

        if (field.getIsKing()) {
            int i = field.getRow()+3;
            int j = field.getColumn()-3;
            Field f = getFieldAt(i, j);
            while (f != null) {
                possibleMoves.add(f);
                i++;
                j--;
                f = getFieldAt(i, j);
            }
            i = field.getRow()+3;
            j = field.getColumn()+3;
            f = getFieldAt(i,j);
            while (f != null) {
                possibleMoves.add(f);
                i++;
                j++;
                f = getFieldAt(i, j);
            }
            i = field.getRow()-3;
            j = field.getColumn()-3;
            f = getFieldAt(i,j);
            while (f != null) {
                possibleMoves.add(f);
                i--;
                j--;
                f = getFieldAt(i, j);
            }
            i = field.getRow()-3;
            j = field.getColumn()+3;
            f = getFieldAt(i,j);
            while (f != null) {
                possibleMoves.add(f);
                i--;
                j++;
                f = getFieldAt(i, j);
            }
        }
        return possibleMoves;
    }

    /**
     * @param field field from which the piece is being removed
     * @param color color of player who made a move
     * @return true, if there is a capture from given field, false otherwise
     */
    protected boolean isCapturePossible(Field field, Color color) {

        ArrayList<Field> possibleMoves = getPossibleCapturesList(field);

        for (Field f:possibleMoves) {
            if(f != null && rules.isMoveValid(field, f, findCapturedFields(field, f), color)) {
                if(!findCapturedFields(field,f).stream().allMatch(fi -> fi.getColor() == Color.NONE) && findCapturedFields(field,f).stream().noneMatch(fi -> fi.getColor() == color)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * @param color color of pieces to be checked
     * @return true, if there is no other move for given color, false otherwise
     */
    protected boolean isGameOver(Color color) {
        if (fields.stream().noneMatch(f -> f.getColor() == color)) {
            return true;
        }
        return fields.stream().noneMatch(f -> f.getColor() == color && isAnotherMovePossible(f, color));
    }

    /**
     * @param field field from which piece is being removed
     * @param color  color of player who made the move
     * @return list of fields in standard move range
     */
    protected ArrayList<Field> getPossibleStandardMovesList(Field field, Color color) {
        ArrayList<Field> possibleMoves = new ArrayList<>();
        int bias = 1;
        if (color == Color.WHITE) {
            bias = -1;
        }
        //standard moves
        possibleMoves.add(getFieldAt(field.getRow()+bias,field.getColumn()-1));
        possibleMoves.add(getFieldAt(field.getRow()+bias,field.getColumn()+1));


        if (field.getIsKing()) {
            int i = field.getRow() + 2;
            int j = field.getColumn() - 2;
            Field f = getFieldAt(i, j);
            while (f != null) {
                possibleMoves.add(f);
                i++;
                j--;
                f = getFieldAt(i, j);
            }
            i = field.getRow() + 2;
            j = field.getColumn() + 2;
            f = getFieldAt(i, j);
            while (f != null) {
                possibleMoves.add(f);
                i++;
                j++;
                f = getFieldAt(i, j);
            }
            i = field.getRow() - 2;
            j = field.getColumn() - 2;
            f = getFieldAt(i, j);
            while (f != null) {
                possibleMoves.add(f);
                i--;
                j--;
                f = getFieldAt(i, j);
            }
            i = field.getRow() - 2;
            j = field.getColumn() + 2;
            f = getFieldAt(i, j);
            while (f != null) {
                possibleMoves.add(f);
                i--;
                j++;
                f = getFieldAt(i, j);
            }
        }
        return possibleMoves;

    }

    /**
     * @param field field from which piece is being removed
     * @param color color of player who made the move
     * @return true, if there is any move from given field to do, false otherwise
     */
    protected boolean isAnotherMovePossible(Field field, Color color) {

        if(field == null) {
            return false;
        }
        if (isCapturePossible(field, color)) {
            System.out.println("MOZLIWE BICIE: "+field.getRow()+" "+field.getColumn());
            return true;
        }
        if (isStandardMovePossible(field, color)) {
            System.out.println("MOZLIWY RUCH: "+field.getRow()+" "+field.getColumn());
            return true;
        }
        return false;
    }

    /**
     * @return number of rows of the board
     */
    protected abstract int getNoRows();

    /**
     * @return random field of a given color
     */
    protected Field getRandomField(Color color) {
        Random random = new Random();
        Field f = getFieldAt(random.nextInt(getNoRows()), random.nextInt(getNoRows()));
        while(f == null || f.getColor() != color) {
            f = getFieldAt(random.nextInt(getNoRows()), random.nextInt(getNoRows()));
        }
        return  f;
    }

    /**
     * @param color color of a player who's supposed to make a move
     * @return list of coordinates: oldX, oldY, newX, newY
     */
    public ArrayList<Integer> getMove(Color color) {
        ArrayList<Integer> cords = new ArrayList<>();
        Field randomField = getRandomField(color);
        if(isAnyCapture(color)) {
            //System.out.println("JEST MOZLIWE BICIE");
            while(!isCapturePossible(randomField, color)) {
                randomField = getRandomField(color);
                //System.out.println("wyszukiwanie pola z biciem");
            }
            System.out.println("ZNALEZIONO POLE Z MOZLIWYM BICIEM "+randomField.getRow()+" "+randomField.getColumn());
            cords.add(randomField.getRow());
            cords.add(randomField.getColumn());
            ArrayList<Field> possibleMoves = getPossibleCapturesList(randomField);
            Random random = new Random();
            possibleMoves.removeAll(Collections.singleton(null));
            Field f = possibleMoves.get(random.nextInt(possibleMoves.size()));
            while(!rules.isMoveValid(randomField, f, findCapturedFields(randomField, f), color) || findCapturedFields(randomField,f).stream().allMatch(fi -> fi.getColor() == Color.NONE) ) {
                //System.out.println("wyszukiwanie legalnego bicia "+f.getRow()+" "+f.getColumn());
                possibleMoves.remove(f);
                f = possibleMoves.get(random.nextInt(possibleMoves.size()));
            }
            //System.out.println(possibleMoves.size());
            System.out.println("ZNALEZIONO BICIE"+f.getRow()+" "+f.getColumn());
            cords.add(f.getRow());
            cords.add(f.getColumn());

        }
        else {
            //System.out.println("NIE MA BICIA");
            Random random = new Random();
            while(!isStandardMovePossible(randomField, color)) {
                randomField = getRandomField(color);
                //System.out.println(randomField.getRow()+ " "+ randomField.getColumn()+ " wyszukiwanie pola z legalnym ruchem");
            }
            System.out.println("ZNALEZIONO POLE Z LEGALNYM RUCHEM: "+randomField.getRow()+" "+randomField.getColumn());
            ArrayList<Field> possibleMoves = getPossibleStandardMovesList(randomField, color);
            possibleMoves.removeAll(Collections.singleton(null));
            Field f = possibleMoves.get(random.nextInt(possibleMoves.size()));
            cords.add(randomField.getRow());
            cords.add(randomField.getColumn());

            while (!rules.isMoveValid(randomField, f, findCapturedFields(randomField, f), color)) {
                //System.out.println("ruch na "+f.getRow()+" "+f.getColumn());
                possibleMoves.remove(f);
                f = possibleMoves.get(random.nextInt(possibleMoves.size()));

                //System.out.println("wyszukiwanie legalnego ruchu");
            }
            System.out.println("ZNALEZIONO LEGALNY RUCH"+f.getRow()+" "+f.getColumn());
            cords.add(f.getRow());
            cords.add(f.getColumn());
        }

        return cords;
    }

    /**
     * @param field field from which the piece is being removed
     * @param color color of a player who made a move
     * @return true if there is a standard move to make, false otherwise
     */
    protected boolean isStandardMovePossible(Field field, Color color) {
        ArrayList<Field> possibleMoves = getPossibleStandardMovesList(field, color);

        for (Field f:possibleMoves) {
            if(f != null && rules.isMoveValid(field, f, findCapturedFields(field, f), color)) {
                return true;
            }
        }
        return false;
    }
}