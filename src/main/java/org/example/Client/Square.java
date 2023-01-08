package org.example.Client;


import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import static javafx.scene.paint.Color.DARKSALMON;

public class Square extends Rectangle {


    private int row;
    private int column;

    public Square() {
        setHeight(10);
        setFill(DARKSALMON);
        setStrokeWidth(2);
    }
}
