package org.example.model;

/**
 * Component of board, contains information about its location and piece which occupies it
 */
public class Field {

    private final int row;
    private final int column;
    /**
     * color of player whose piece stands on this field
     */
    private Color color;
    private boolean isKing = false;

    public Field(int row, int column, Color color) {
        this.row = row;
        this.column = column;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public boolean getIsKing() {
        return isKing;
    }

    public void setIsKing(boolean isKing) {
        this.isKing = isKing;
    }
}
