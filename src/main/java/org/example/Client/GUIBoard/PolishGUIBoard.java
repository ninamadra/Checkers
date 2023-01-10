package org.example.Client.GUIBoard;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.Client.GameController;
import org.example.Client.Square;

public class PolishGUIBoard extends AbstractGUIBoard {
    public PolishGUIBoard(GameController gc) {
        super(gc);
        int count = 0;
        double size = 800 / 10;
        for (int j = 0; j < 10; j++) {
            count++;
            for (int i = 0; i < 10; i++) {
                Rectangle rectangle = new Rectangle(size, size, size, size);
                rectangle.setFill(Color.WHITESMOKE);
                if (count % 2 == 0) {
                    rectangle = new Square(i, 9-j, size, this);
                    squares.add((Square)rectangle);
                    if(j < 4) {
                        ((Square) rectangle).setWhitePawn();
                    }
                    if(j > 5) {
                        ((Square) rectangle).setBlackPawn();
                    }
                }
                add(rectangle, i, j);
                count++;
            }
        }
    }

    @Override
    protected int getNoRows() {
        return 10;
    }
}
