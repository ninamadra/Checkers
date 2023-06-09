package org.example.Server;

import org.example.model.Color;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class Bot implements Observer  {
    private final GameRoom gameRoom;
    private final Color color;

    public Bot(GameRoom gameRoom) {
        this.gameRoom = gameRoom;
        color = gameRoom.getColorToSet();
    }
    @Override
    public void updateObserver(String message) {
        List<String> items = Arrays.asList(message.split(" "));
        System.out.println(items.get(0));
        switch (items.get(0)) {
            case "MOVED" ->  {
                switch(items.get(5)) {
                    case "BLACK" -> {
                        System.out.println("BOT MOVE");
                        gameRoom.performAction("BOTMOVE", this);
                    }
                }
            }
        }
    }

    @Override
    public Socket getSocket() {
        return null;
    }


    @Override
    public Color getColor() {
        return color;
    }


}

