package org.example.Client;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ClassicGUIBoard extends AbstractGUIBoard {

    ClassicGUIBoard() {
        int count = 0;
        double s = 100;
        for (int j = 0; j < 8; j++) {
            count++;
            for (int i = 0; i < 8; i++) {
                Rectangle rectangle = new Rectangle(s, s, s, s);
                rectangle.setFill(Color.WHITESMOKE);
                if (count % 2 == 0) {
                    rectangle = new Square(i, 7-j);
                }
                add(rectangle, i, j);
                count++;
            }
        }
    }
}
