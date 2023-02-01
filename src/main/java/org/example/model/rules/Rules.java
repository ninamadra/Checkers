package org.example.model.rules;

import org.example.model.Color;
import org.example.model.Field;

import java.util.ArrayList;

/**
 * Interface which allows boards to check moves according to different sets of rules
 */
public interface Rules {
    /**
     * @param oldField field from which the piece is being removed
     * @param newField desired location of the piece
     * @param capturedFields list of fields captured on the way
     * @param playerColor color of player who made move
     * @return true, if move is ok according to rules and false otherwise
     */
    boolean isMoveValid(Field oldField, Field newField, ArrayList<Field> capturedFields, Color playerColor);

    /**
     * @return true, if capturing is obligatory according to the rules or false otherwise
     */
    boolean isCapturingObligatory();
}
