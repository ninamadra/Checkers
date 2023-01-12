package org.example.model;

import org.example.model.board.*;


public class Game {
    private AbstractBoard board;
    public void setVariant(String variant) {

        switch (variant) {
            case "CLASSIC" -> board = new ClassicBoard();
            case "THAI" -> board = new ThaiBoard();
            case "POLISH" -> board = new PolishBoard();
        }

    }
    //TODO implement makeMove via part of chain of responsibility
    public String makeMove(int oldX, int oldY, int newX, int newY, Color color) throws illegalMoveException, GameOverException {
        System.out.println(oldX + " " + oldY + " " + newX + " " + newY + " " + color);
            try {
                board.move(oldX, oldY, newX, newY, getTurn());
            }
            catch(illegalMoveException moveException) {
                System.out.println("move");
                throw moveException;
            } catch (GameOverException gameOverException) {
                System.out.println("over");
                throw gameOverException;
            }
            return "MOVED " + oldX + " " + oldY + " " + newX + " " + newY + " " + getTurn();

    }

    public Color getTurn() {
        return board.getTurn();
    }
}
