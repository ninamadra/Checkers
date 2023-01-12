package org.example.model;

/**
 * Enum with information about player's color
 */
public enum Color {
    WHITE,
    BLACK,
    NONE;

    /**
     * @return color of opposite player or NONE
     */
    public Color getOppositeColor() {
        if (this == WHITE) {
            return BLACK;
        } else if (this == BLACK) {
            return WHITE;
        }
        return NONE;
    }
}

