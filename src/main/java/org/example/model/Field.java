package org.example.model;

public class Field {

    private final int row;
    private final int column;
    private boolean isOccupied;
    private Color color;

    Field(int row, int column) {
        this.row = row;
        this.column = column;
    }

    boolean getIsOccupied() {
        return isOccupied;
    }

    Color getColor() {
        return color;
    }

    void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    void setColor(Color color) {
        this.color = color;
    }

    int getRow() {
        return this.row;
    }
}
