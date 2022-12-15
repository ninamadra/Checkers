package org.example.model.rules;

import org.example.model.Color;
import org.example.model.Field;

public abstract class AbstractRules {
    public boolean isMoveValid(Field oldField, Field newField, Color playerColor) {
        if(newField.getIsOccupied()) {
            return false;
        }
        if(oldField.getColor() != playerColor) {
            return false;
        }
        if(isInRange(oldField, newField) || isInCaptureRange(oldField,newField)) {
            return true;
        }
        return false;
    }
    public abstract boolean isWin();
    protected abstract boolean isInRange(Field oldField, Field newField);
    protected abstract boolean isInCaptureRange(Field oldField, Field newField);


}
