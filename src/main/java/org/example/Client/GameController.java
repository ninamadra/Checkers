package org.example.Client;

import org.example.model.Color;


public class GameController {
    private final Game game;
    private final Connection con;
    
    public GameController() {
        game = new Game();
        con = new Connection(this);
    }
    
    public Color getColor() {
        return game.getClientColor();
    }
    public void setColor(String color) {
        game.setClientColor(color);
    }
}
