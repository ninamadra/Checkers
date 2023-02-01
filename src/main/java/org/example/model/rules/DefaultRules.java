package org.example.model.rules;

import org.example.model.Color;
import org.example.model.Field;

import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * Implementation of Rules interface, contains information about rules and methods to check if move is valid
 */
public class DefaultRules implements Rules {
    /**
     * @param oldField field from which the piece is being removed
     * @param newField desired location of the piece
     * @param capturedFields list of fields captured on the way
     * @param playerColor color of player who made move
     * @return true, if move is ok according to rules and false otherwise
     */
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
        int counter = 0;
        if (oldField.getRow() < newField.getRow()) {
            for (int i = 0; i < capturedFields.size() - 1; i++) {
                if (capturedFields.get(i).getColor() == playerColor) {
                    return false;
                }
                if (capturedFields.get(i).getColor() == playerColor.getOppositeColor() && capturedFields.get(i + 1).getColor() != Color.NONE) {
                    return false;
                }
                if(capturedFields.get(i).getColor() == playerColor.getOppositeColor()) {
                    counter++;
                    if(counter > 1) {
                        return false;
                    }
                }
            }
        }
        else {
            for (int i = capturedFields.size() - 1; i > 0; i--) {
                if (capturedFields.get(i).getColor() == playerColor) {
                    return false;
                }
                if (capturedFields.get(i).getColor() == playerColor.getOppositeColor() && capturedFields.get(i - 1).getColor() != Color.NONE) {
                    return false;
                }
                if(capturedFields.get(i).getColor() == playerColor.getOppositeColor()) {
                    counter++;
                    if(counter > 1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * @return true, if capturing is obligatory according to the rules or false otherwise
     */
    @Override
    public boolean isCapturingObligatory() {
        return true;
    }

    /**
     * @param oldField field from which the piece is being removed
     * @param newField desired location of the piece
     * @param color color of player who made a move
     * @return true, if fields are in standard piece move distance, false otherwise
     */
    protected boolean isInRange(Field oldField, Field newField, Color color) {
        if(color == Color.BLACK) {
            return oldField.getRow() + 1 == newField.getRow() && abs(oldField.getColumn() - newField.getColumn()) == 1;
        }
        else return oldField.getRow() - 1 == newField.getRow() && abs(oldField.getColumn() - newField.getColumn()) == 1;
    }

    /**
     * @param oldField field from which the piece is being removed
     * @param newField desired location of the piece
     * @return true, if fields are in standard piece capture distance, false otherwise
     */
    protected boolean isInCaptureRange(Field oldField, Field newField) {
        return abs(oldField.getRow() - newField.getRow()) == 2 && abs(oldField.getColumn() - newField.getColumn()) == 2;
    }

    /**
     * @param oldField field from which the piece is being removed
     * @param newField desired location of the piece
     * @return true, if fields are in king move distance, false otherwise
     */
    protected boolean isInKingRange(Field oldField, Field newField) {
        return abs(oldField.getRow() - newField.getRow()) == abs(oldField.getColumn() - newField.getColumn());
    }

    /**
     * @param oldField field from which the piece is being removed
     * @param newField desired location of the piece
     * @return true, if fields are in king capture distance, false otherwise
     */
    protected boolean isInKingCaptureRange(Field oldField, Field newField) {
        return abs(oldField.getRow() - newField.getRow()) == abs(oldField.getColumn() - newField.getColumn());
    }
}
