package org.example.Client;


import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import org.example.Client.GUIBoard.GUIBoard;

public class Square extends Rectangle {

    private final int row;
    private final int column;
    private boolean isKing = false;
    private final GUIBoard board;
    

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

    public void setEmpty() {
        setFill(Color.BLACK);
    }

    public void setWhitePawn() {
        setFill(new ImagePattern(new Image("/org.example/redPiece.png")));
    }

    public void setWhiteKing() {
        setFill(new ImagePattern(new Image("/org.example/redQueen.png")));
    }

    public void setBlackPawn() {
        setFill(new ImagePattern(new Image("/org.example/blackPiece.png")));
    }
    public void setBlackKing() {
        setFill(new ImagePattern(new Image("/org.example/blackQueen.png")));
    }

    public void highlight() {
        setStroke(Color.GOLD);
    }

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
}
