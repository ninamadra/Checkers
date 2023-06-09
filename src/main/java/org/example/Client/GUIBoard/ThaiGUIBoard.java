package org.example.Client.GUIBoard;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.Client.GameController;
import org.example.Client.Square;
/**
 * Implementation of thai variant board
 */
public class ThaiGUIBoard extends AbstractGUIBoard {
    /**
     * Constructor which sets up thai board, pawns etc.
     * @param gc main game controller
     */
    public ThaiGUIBoard(GameController gc) {
        super(gc);
        int count = 0;
        double size = 800 / 8;
        for (int j = 0; j < 8; j++) {
            count++;
            for (int i = 0; i < 8; i++) {
                Rectangle rectangle = new Rectangle(size, size, size, size);
                rectangle.setFill(Color.WHITESMOKE);
                if (count % 2 == 0) {
                    rectangle = new Square(i, 7-j, size, this);
                    squares.add((Square)rectangle);
                    if(j < 2) {
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
        return 8;
    }
}
