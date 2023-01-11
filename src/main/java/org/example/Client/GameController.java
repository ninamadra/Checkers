package org.example.Client;

import org.example.model.Color;


public class GameController {
    private final Game game;
    private final Connection con;
    private final GUI gui;
    
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

    public void startGame(String variant) {
        con.startGame(variant);
    }

    public void tryMove(int row, int column, int newRow, int newColumn) {
        con.makeMove(row, column, newRow, newColumn, game.getTurn());
    }

    public void makeMove(int oldX, int oldY, int newX, int newY, Color color) {
        gui.displayMove(oldX, oldY, newX, newY, color);
        game.setTurn(color);
    }

    public Color getTurn() {
        return game.getTurn();
    }

    public void setType(String var) {
        gui.setType(var);
    }
    public void displayMsg(String msg) {
        gui.displayAnnouncement(msg);
    }
}