package org.example.Client;

import org.example.model.Color;


/**
 * Class which is controller of client site game.
 */
public class GameController {
    private final Game game;
    private final Connection con;
    private final GUI gui;

    /**
     * Basic constructor
     * @param g GUI view
     */
    public GameController(GUI g) {
        game = new Game();
        con = new Connection(this);
        gui = g;
    }
    
    public Color getColor() {
        return game.getClientColor();
    }

    public void setColor(String color) {
        game.setClientColor(color);
    }

    /**
     * Method passes request to start a specific game type
     * @param variant of the game
     */
    public void startGame(String variant) {
        con.startGame(variant);
    }

    /**
     * Method passes request to make a move
     * @param row old row index
     * @param column old column index
     * @param newRow new row index
     * @param newColumn new column index
     */
    public void tryMove(int row, int column, int newRow, int newColumn) {
        con.makeMove(row, column, newRow, newColumn, game.getTurn());
    }

    /**
     * Method passes accepted move to the GUI
     * @param oldX old row index
     * @param oldY old column index
     * @param newX new row index
     * @param newY new column index
     * @param color of player who is on move
     */
    public void makeMove(int oldX, int oldY, int newX, int newY, Color color) {
        gui.displayMove(oldX, oldY, newX, newY, color);
        game.setTurn(color);
    }

    public Color getTurn() {
        return game.getTurn();
    }

    /**
     * Method passes request to generate proper board and start the game by setting its type
     * @param var  of the game
     */
    public void setType(String var) {
        gui.setType(var);
    }

    /**
     * Method passes command to display in GUI
     * @param msg external command/message
     */
    public void displayMsg(String msg) {
        gui.displayAnnouncement(msg);
    }
    public void stop() {
        gui.disable();
    }
}