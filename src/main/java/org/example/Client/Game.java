package org.example.Client;

import org.example.model.Color;

import java.util.Arrays;
import java.util.List;

/**
 * Class of game model. IT contains properties of the game
 */
public class Game {
    private Color clientColor;
    private Color turn = Color.WHITE;


    /**
     * Unload message from server about clients color
     * @param clientColor
     */
    public void setClientColor(String clientColor) {
        List<String> items = Arrays.asList(clientColor.split(" "));
        this.clientColor = Color.valueOf(items.get(1));
    }
    public Color getClientColor() {
        return clientColor;
    }

    public Color getTurn() {
        return turn;
    }

    public void setTurn(Color color) {
        turn = color;
    }
}
