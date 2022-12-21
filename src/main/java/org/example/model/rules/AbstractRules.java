package org.example.model.rules;

import org.example.model.Color;
import org.example.model.Field;

import java.util.ArrayList;

import static java.lang.Math.abs;

public abstract class AbstractRules {
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
        if (!isInRange(oldField, newField) || !isInCaptureRange(oldField, newField) || (oldField.getIsKing() && (!isInKingRange(oldField, newField) || !isInKingCaptureRange(oldField, newField)))) {
            return false;
        }

        for ( Field field: capturedFields) {
            if(field.getColor() == playerColor) {
                return false;
            }
        }
        if (capturedFields.size() == 1 && capturedFields.get(0).getColor() != playerColor.getOppositeColor()) {
            return false; //moze da sie za jednym zamachecehm
        }
        //TODO sprawdzenie bicia krola (czy robi dobry skok, tj miedzy biciami jest jedno pole przerwy itd

        return true;
    }

    protected boolean isInRange(Field oldField, Field newField) {
        return oldField.getRow() + 1 == newField.getRow() && abs(oldField.getColumn() - newField.getColumn()) == 1;
    }
    protected boolean isInCaptureRange(Field oldField, Field newField) {
        return abs(oldField.getRow() - newField.getRow()) == 2 && abs(oldField.getColumn() - newField.getColumn()) == 2;
    }

    protected boolean isInKingRange(Field oldField, Field newField) {
        return abs(oldField.getRow() - newField.getRow()) == 1 && abs(oldField.getColumn() - newField.getColumn()) == 1;
    }
    protected boolean isInKingCaptureRange(Field oldField, Field newField) {
        return abs(oldField.getRow() - newField.getRow()) == 2 && abs(oldField.getColumn() - newField.getColumn()) == 2;
    }
}
