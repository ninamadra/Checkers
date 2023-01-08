package org.example.model.rules;

import org.example.model.Color;
import org.example.model.Field;

import java.util.ArrayList;

public interface Rules {
    boolean isMoveValid(Field oldField, Field newField, ArrayList<Field> capturedFields, Color playerColor);
    boolean isCapturingObligatory();
}
