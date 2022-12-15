package org.example.model;

public class Field {

    private final int row;
    private final int column;
    private boolean isOccupied;
    private Color color;

    public Field(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public boolean getIsOccupied() {
        return isOccupied;
    }

    public Color getColor() {
        return color;
    }

    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getRow() {
        return this.row;
    }
}
