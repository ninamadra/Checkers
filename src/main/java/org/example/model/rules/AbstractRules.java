package org.example.model.rules;

import org.example.model.Color;
import org.example.model.Field;

import java.util.ArrayList;

import static java.lang.Math.abs;

public abstract class AbstractRules {
    public boolean isMoveValid(Field oldField, Field newField, Color playerColor) {
        if(oldField == null || newField == null) {
            return false;
        }
        if(oldField.getColor() != playerColor) {
            return false;
        }
        if(newField.getColor() != Color.NONE) {
            return false;
        }

        return isInRange(oldField, newField) || (isInCaptureRange(oldField, newField) || ( oldField.getIsKing() && (isInKingRange(oldField,newField) || isInKingCaptureRange(oldField, newField))));
    }
    public boolean isWin(ArrayList<Field> fields, Color color) {
       // if(fields.stream().filter(f -> f.getColor().equals(color)).allMatch(f -> f.getColor().equals(color))) {
         //   return true;
        //}
        return false;
    }
    protected boolean isInRange(Field oldField, Field newField) {
        if(oldField.getRow()+1 != newField.getRow() || abs(oldField.getColumn()- newField.getColumn()) != 1) {
            return false;
        }
        return true;
    }
    protected boolean isInCaptureRange(Field oldField, Field newField) {
        if(oldField.getRow()+2 != newField.getRow() || abs(oldField.getColumn()- newField.getColumn()) != 2) {
            return false;
        }

        return true;
    }

    protected boolean isInKingRange(Field oldField, Field newField) {
        if(abs(oldField.getRow()- newField.getRow()) != 1 || abs(oldField.getColumn()- newField.getColumn()) != 1) {
            return false;
        }
        return true;
    }
    protected boolean isInKingCaptureRange(Field oldField, Field newField) {
        if(abs(oldField.getRow()- newField.getRow()) != 2 || abs(oldField.getColumn()- newField.getColumn()) != 2) {
            return false;
        }
        return true;
    }
}
