package org.example.Server;

import org.example.model.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GameRoom implements GameRoomMediator{
    private Game game;
    ArrayList<Observer> clients;

    public void createGame(String gameType) {
        game = new Game();
        game.setVariant(gameType);
    }

    @Override
    public void attachObserver(Observer client) {
        clients.add(client);
    }

    @Override
    public void detachObserver(Observer client) {
        clients.remove(client);
        try {
            client.getSocket().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void noticeAction(String command) {
        for (Observer client: clients) {
            client.updateObserver(command);
        }
    }

    //TODO implement executing a proper method
    public String performAction(String command) {
        List<String> items = Arrays.asList(command.split("\\s*,\\s*"));
        return " ";
    }

}
