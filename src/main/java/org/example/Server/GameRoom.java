package org.example.Server;

import org.example.model.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GameRoom implements GameRoomMediator{
    private static GameRoom gameRoom = null;
    private Game game = null;
    ArrayList<Observer> clients;

    public static GameRoom getInstance() {
        if(gameRoom == null) {
            synchronized (Server.class) {
                if(gameRoom == null) {
                    gameRoom = new GameRoom();
                }
            }
        }
        return gameRoom;
    }

    //Important to execute one method at the time
    public synchronized void createGame(String gameType) throws creationException {
        if(game == null) {
            game = new Game();
            game.setVariant(gameType);
        }
        else
            throw new creationException();
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
    public String performAction(String command, Observer observer) {
        List<String> items = Arrays.asList(command.split("\\s*,\\s*"));
        String answer;
        switch (items.get(0)) {
            case "START":
                try {
                    createGame(items.get(1));
                } catch (creationException e) {
                    return "FAIL GAME_EXISTS";
                }
                break;
            case "MOVE":
                game.makeMove(Integer.parseInt(items.get(1)), Integer.parseInt(items.get(2)), Integer.parseInt(items.get(3)), Integer.parseInt(items.get(4)), observer.getColor());
        }

        return " ";
    }

}
