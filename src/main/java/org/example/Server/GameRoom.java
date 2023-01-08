package org.example.Server;

import org.example.model.Color;
import org.example.model.Game;
import org.example.model.board.illegalMoveException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameRoom implements GameRoomMediator{
    private static GameRoom gameRoom = null;
    private Game game = null;
    private final ArrayList<Observer> clients;
    private final List<Color> colors;

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
    public GameRoom() {
        //arrange colors list
        colors = new ArrayList<>();
        Collections.addAll(colors, Color.values());
        colors.remove(Color.NONE);
        clients = new ArrayList<>();
    }
    public Color getColorToSet() {
        Color col = colors.get(0);
        colors.remove(0);
        return col;
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


    public void performAction(String command, Observer observer) {
        List<String> items = Arrays.asList(command.split("\\s*,\\s*"));
        switch (items.get(0)) {
            case "START" -> {
                try {
                    createGame(items.get(1));
                    noticeAction("STARTED");
                } catch (creationException e) {
                    observer.updateObserver("FAIL GAME_EXISTS");
                }
            }
            case "MOVE" -> {
                if(observer.getColor().equals(game.getTurn())) {
                    try {
                        noticeAction(game.makeMove(Integer.parseInt(items.get(1)), Integer.parseInt(items.get(2)), Integer.parseInt(items.get(3)), Integer.parseInt(items.get(4)), observer.getColor()));
                    } catch (illegalMoveException e) {
                        observer.updateObserver("FAIL WRONG_MOVE");
                    }
                }
                else {
                    observer.updateObserver("FAIL NOT_YOUR_TURN");
                }
            }
            //TODO ending the game
            case "LEAVE" -> {
                detachObserver(observer);
                noticeAction("END");
            }
        }
    }

}
