package org.example.Client;

import org.example.model.Color;

import java.util.Arrays;
import java.util.List;

public class Game {
    private Color clientColor;
    private Color turn = Color.WHITE;

    public void setClientColor(String clientColor) {
        List<String> items = Arrays.asList(clientColor.split(" "));
        this.clientColor = Color.valueOf(items.get(1));
    }
}
