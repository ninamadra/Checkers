package org.example.model.rules;

import org.example.model.Color;
import org.example.model.Field;

public class ThaiRules extends AbstractRules {

    @Override
    public boolean isWin() {
        return false;
    }

    @Override
    protected boolean isInRange(Field oldField, Field newField) {
        return false;
    }

    @Override
    protected boolean isInCaptureRange(Field oldField, Field newField) {
        return false;
    }
}
