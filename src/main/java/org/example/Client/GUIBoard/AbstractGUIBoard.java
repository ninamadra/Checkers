package org.example.Client.GUIBoard;

import javafx.scene.layout.GridPane;
import org.example.Client.GameController;
import org.example.Client.Square;
import org.example.model.Color;

import java.util.ArrayList;

/**
 * Class which represent view of board
 */
public abstract class AbstractGUIBoard extends GridPane implements GUIBoard {
    protected ArrayList<Square> squares;
    protected Square clicked;
    protected GameController gameController;

    /**
     * Basic constructor with injection
     * @param gc
     */
    public AbstractGUIBoard(GameController gc) {
        squares = new ArrayList<>();
        gameController = gc;
    }

    /**Method for proper updating board view. Removing taken pawns and moving
     * @param oldX old x-axis cord
     * @param oldY old y-axis cord
     * @param newX new x-axis cord
     * @param newY new y-axis cord
     * @param color
     */
    public void updateBoard(int oldX, int oldY, int newX, int newY, Color color) {

        Square oldSquare = squares.stream().filter(f -> f.getRow() == oldX && f.getColumn() == oldY).findFirst().orElse(null);
        Square newSquare = squares.stream().filter(f -> f.getRow() == newX && f.getColumn() == newY).findFirst().orElse(null);

        int i, j, maxRow, bias = 1;
        if (oldSquare.getRow() < newSquare.getRow()) {
            i = oldSquare.getRow()+1;
            maxRow = newSquare.getRow();
            j = oldSquare.getColumn();
            if ( j > newSquare.getColumn()) { bias = -1; }
        }
        else {
            i = newSquare.getRow()+1;
            maxRow = oldSquare.getRow();
            j = newSquare.getColumn();
            if ( j > oldSquare.getColumn()) { bias = -1; }
        }
        j += bias;

        while ( i < maxRow) {
            int finalI1 = i;
            int finalJ1 = j;
            Square temp = squares.stream().filter(f -> f.getRow() == finalI1 && f.getColumn() == finalJ1).findFirst().orElse(null);
            if (temp != null) {
                temp.setEmpty();
                temp.setKing(false);
            }
            i++;
            j+=bias;
        }

        newSquare.setKing(oldSquare.isKing());

        if(oldSquare.getColor() == Color.WHITE) {
            if (oldSquare.isKing() || newSquare.getRow() == 0) {
                newSquare.setWhiteKing();
                newSquare.setKing(true);
            }
            else { newSquare.setWhitePawn(); }
        }
        else if (oldSquare.getColor() == Color.BLACK) {
            if (oldSquare.isKing() || newSquare.getRow() == getNoRows()-1 ) {
                newSquare.setBlackKing();
                newSquare.setKing(true);
            }
            else { newSquare.setBlackPawn(); }
        }

        oldSquare.setEmpty();
        oldSquare.setKing(false);

    }

    /**
     * Method which provides proper behavior while clicking
     * @param clicked
     */
    public void respondToClick(Square clicked) {
        if (this.clicked == clicked) {
            this.clicked.unhighlight();
            this.clicked = null;
        }
        else {
            if(this.clicked != null) {
                if(gameController.getColor() == gameController.getTurn()) {
                    gameController.tryMove(this.clicked.getRow(), this.clicked.getColumn(), clicked.getRow(), clicked.getColumn());
                    this.clicked.unhighlight();
                    this.clicked = null;

                }
            }
            else {
                clicked.highlight();
                this.clicked = clicked;
            }
        }
    }

    /**
     * @return number of current's board rows
     */
    protected abstract int getNoRows();
}
