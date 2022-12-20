package org.example.model.board;

import org.example.model.Color;
import org.example.model.Field;
import org.example.model.rules.AbstractRules;

import java.util.ArrayList;

import static java.lang.Math.max;
import static java.lang.Math.min;

public abstract class AbstractBoard {

    protected AbstractRules rules;
    protected ArrayList<Field> fields;
    protected Color turn = Color.WHITE;

    public ArrayList<Field> getFields() {
        return fields;
    }
    public Field getFieldAt(int x, int y) {
        return fields.stream().filter(f -> f.getColumn() == x && f.getRow() == y).findFirst().orElse(null);
    }
    public void move(int oldX, int oldY, int newX, int newY, Color color) throws illegalMoveException {
        Field oldField = fields.stream().filter(f -> f.getColumn() == oldX && f.getRow() == oldY).findFirst().orElse(null);
        Field newField = fields.stream().filter(f -> f.getColumn() == newX && f.getRow() == newY).findFirst().orElse(null);

        if(!rules.isMoveValid(oldField, newField, color)) {
            throw new illegalMoveException();
        }

        newField.setColor(oldField.getColor());
        oldField.setColor(Color.NONE);
        newField.setIsKing(oldField.getIsKing());
        oldField.setIsKing(false);

        boolean capture = false;
        int j = min(oldY, newY)+1;
        for ( int i = min(oldX, newX)+1; i < max(oldX, newX); i++) {
            capture = true;
            int finalI = i;
            int finalJ = j;
            Field capturedField = fields.stream().filter(f -> f.getColumn() == finalI && f.getRow() == finalJ).findFirst().orElse(null);
            capturedField.setColor(Color.NONE);
            capturedField.setIsKing(false);

            j++;
        }
        if(isGameOver()) {
            turn = Color.NONE;
        }
        else if(!(isAnotherMovePossible(color) && capture)) {
            turn = turn.getOppositeColor();
        }
    }
    public boolean isAnotherMovePossible(Color color) {return false;}
    public boolean isGameOver() { return false;}

    public Color getTurn() { return turn; }
}
