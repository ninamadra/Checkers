package org.example.Client;


import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import static javafx.scene.paint.Color.DARKSALMON;

public class Square extends Rectangle {

    private int row;
    private int column;
    

    public Square(int row, int column, double size) {

        this.row = row;
        this.column = column;
        setWidth(size);
        setHeight(size);
        setFill(Color.BLACK);
        setOnMouseClicked(e -> {
            System.out.println(row+" "+column);
        });
    }

    private void setEmpty() {
        setFill(Color.BLACK);
    }

    public void setWhitePawn() {
        setFill(new ImagePattern(new Image("blackPiece.png")));
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
