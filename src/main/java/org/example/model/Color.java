package org.example.model;

public enum Color {
    WHITE,
    BLACK,
    NONE;

    public Color getOppositeColor() {
        if (this == WHITE) {
            return BLACK;
        } else if (this == BLACK) {
            return WHITE;
        }
        return NONE;
    }
}

