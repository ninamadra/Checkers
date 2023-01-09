package org.example.Client;


import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import static javafx.scene.paint.Color.DARKSALMON;

public class Square extends Rectangle {

    private int row;
    private int column;
    

    public Square(int row, int column) {

        this.row = row;
        this.column = column;
        setWidth(100);
        setHeight(100);
        setFill(Color.BLACK);
        setOnMouseClicked(e -> {
            System.out.println(row+" "+column);
        });
    }
}
