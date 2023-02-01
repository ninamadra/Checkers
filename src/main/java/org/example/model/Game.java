package org.example.model;

import org.example.model.board.*;

import java.util.ArrayList;


/**
 * A class which holds information about concrete game and performs action on it
 */
public class Game {
    private AbstractBoard board;

    /**
     * Creates board according to chosen variant
     * @param variant game variant
     */
    public void setVariant(String variant) {

        switch (variant) {
            case "CLASSIC" -> board = new ClassicBoard();
            case "THAI" -> board = new ThaiBoard();
            case "POLISH" -> board = new PolishBoard();
        }

    }

    /**
     * @param oldX number of row from which piece is being removed
     * @param oldY number of column from which piece is being removed
     * @param newX number of row of desired new piece location
     * @param newY number of column of desired new piece location
     * @param color color of player who made a move
     * @return prepared string with feedback to the server
     * @throws illegalMoveException if move is invalid
     * @throws GameOverException if game is over
     */
    public String makeMove(int oldX, int oldY, int newX, int newY, Color color) throws illegalMoveException, GameOverException {
        System.out.println(oldX + " " + oldY + " " + newX + " " + newY + " " + color);
            try {
                board.move(oldX, oldY, newX, newY, getTurn());
            }
            catch(illegalMoveException moveException) {
                throw moveException;
            } catch (GameOverException gameOverException) {
                throw gameOverException;
            }
            return "MOVED " + oldX + " " + oldY + " " + newX + " " + newY + " " + getTurn();

    }

    /**
     * @return color of player who will make move next
     */
    public Color getTurn() {
        return board.getTurn();
    }

    public String makeBotMove(Color color) throws illegalMoveException, GameOverException {
        ArrayList<Integer> cords = board.getMove(color);
        return makeMove(cords.get(0), cords.get(1), cords.get(2), cords.get(3), color);
    }
}
