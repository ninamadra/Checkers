package org.example.model.rules;

import org.example.model.Color;
import org.example.model.Field;

import java.util.ArrayList;

public class PolishRules extends AbstractRules {
    @Override
    protected boolean isInRange(Field oldField, Field newField) {
        return false;
    }

    @Override
    protected boolean isInCaptureRange(Field oldField, Field newField) {
        return false;
    }
}
