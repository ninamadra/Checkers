package org.example.Client;


import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import org.example.Client.GUIBoard.GUIBoard;

/**
 * A class which extends JavaFX's Rectangle to simulate one square on the board
 */
public class Square extends Rectangle {

    private final int row;
    private final int column;
    private boolean isKing = false;
    private final GUIBoard board;
    private org.example.model.Color color;

    /**
     * Basic constructor with setting view of class in GUI
     * @param column column address on board
     * @param row row address on board
     * @param size of square
     * @param board to set on
     */
    public Square(int column, int row, double size, GUIBoard board) {

        this.row = row;
        this.column = column;
        this.board = board;

        setWidth(size-2);
        setHeight(size-2);
        setStrokeWidth(2);
        setStroke(Color.BLACK);
        setFill(Color.BLACK);
        setOnMouseClicked(e -> {
            board.respondToClick(this);
        });
    }

    /**
     * Method clears view from image component and color
     */
    public void setEmpty() {
        setFill(Color.BLACK);
        color = org.example.model.Color.NONE;
    }

    public void setWhitePawn() {
        setFill(new ImagePattern(new Image("/org.example/redPiece.png")));
        color = org.example.model.Color.WHITE;
    }

    public void setWhiteKing() {
        setFill(new ImagePattern(new Image("/org.example/redQueen.png")));
        color = org.example.model.Color.WHITE;
    }

    public void setBlackPawn() {
        setFill(new ImagePattern(new Image("/org.example/blackPiece.png")));
        color = org.example.model.Color.BLACK;
    }
    public void setBlackKing() {
        setFill(new ImagePattern(new Image("/org.example/blackQueen.png")));
        color = org.example.model.Color.BLACK;
    }

    /**
     * Method which highlight the borders of square
     */
    public void highlight() {
        setStroke(Color.GOLD);
    }
    /**
     * Method which unhighlight the borders of square
     */
    public void unhighlight() {
        setStroke(Color.BLACK);
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public boolean isKing() {
        return isKing;
    }
    public void setKing(boolean king) {
        isKing = king;
    }

    public org.example.model.Color getColor() {
        return color;
    }
}
