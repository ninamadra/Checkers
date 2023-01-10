package org.example.model.rules;

import org.example.model.Color;
import org.example.model.Field;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class DefaultRules implements Rules {
    public boolean isMoveValid(Field oldField, Field newField, ArrayList<Field> capturedFields, Color playerColor) {
        if (oldField == null || newField == null) {
            return false;
        }
        if (oldField.getColor() != playerColor) {
            return false;
        }
        if (newField.getColor() != Color.NONE) {
            return false;
        }
        if (!(isInRange(oldField, newField, playerColor) || isInCaptureRange(oldField, newField) || (oldField.getIsKing() && (isInKingRange(oldField, newField) || isInKingCaptureRange(oldField, newField))))) {
            return false;
        }

        if (capturedFields.size() == 1 && capturedFields.get(0).getColor() != playerColor.getOppositeColor()) {
            return false;
        }

        for ( int i = 0; i < capturedFields.size()-1; i++) {
            if(capturedFields.get(i).getColor() == playerColor) { return false; }
            if (capturedFields.get(i).getColor() == playerColor.getOppositeColor() && capturedFields.get(i+1).getColor() != Color.NONE) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean isCapturingObligatory() {
        return true;
    }

    protected boolean isInRange(Field oldField, Field newField, Color color) {
        if(color == Color.BLACK) {
            return oldField.getRow() + 1 == newField.getRow() && abs(oldField.getColumn() - newField.getColumn()) == 1;
        }
        else return oldField.getRow() - 1 == newField.getRow() && abs(oldField.getColumn() - newField.getColumn()) == 1;
    }
    protected boolean isInCaptureRange(Field oldField, Field newField) {
        return abs(oldField.getRow() - newField.getRow()) == 2 && abs(oldField.getColumn() - newField.getColumn()) == 2;
    }

    protected boolean isInKingRange(Field oldField, Field newField) {
        return abs(oldField.getRow() - newField.getRow()) == abs(oldField.getColumn() - newField.getColumn());
    }
    protected boolean isInKingCaptureRange(Field oldField, Field newField) {
        return abs(oldField.getRow() - newField.getRow()) == abs(oldField.getColumn() - newField.getColumn());
    }
}
