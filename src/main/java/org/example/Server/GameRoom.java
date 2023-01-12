package org.example.Server;

import org.example.model.Color;
import org.example.model.Game;
import org.example.model.board.GameOverException;
import org.example.model.board.illegalMoveException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A class which is instance of mediator between clients and server. It is also controller of game besides gameplay
 */
public class GameRoom implements GameRoomMediator{
    private static GameRoom gameRoom = null;
    private Game game = null;
    private final ArrayList<Observer> clients;
    private final List<Color> colors;

    /**
     * An implementation of singleton design pattern
     * @return instance of GameRoom class
     */
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

    /**
     * Basic constructor. Besides setting all values it also arranges list of colors for clients in proper way
     */
    public GameRoom() {
        colors = new ArrayList<>();
        Collections.addAll(colors, Color.values());
        colors.remove(Color.NONE);
        clients = new ArrayList<>();
    }

    /**
     * A method for getting client unique color and updating list of colors
     * @return client color
     */
    public Color getColorToSet() {
        Color col = colors.get(0);
        colors.remove(0);
        return col;
    }

    /**
     * A method for creating the game in proper variant
     * @param gameType selected variant of the game
     * @throws creationException
     * @throws capacityException
     */
    public synchronized void createGame(String gameType) throws creationException, capacityException {
        if(clients.size() < 2) {
            throw new capacityException();
        }
        if(game == null) {
            game = new Game();
            game.setVariant(gameType);
        }
        else
            throw new creationException();
    }

    /**
     * Simple method that adds client to the "subscription" list
     * @param client
     */
    @Override
    public void attachObserver(Observer client) {
        clients.add(client);
    }

    /**
     * Simple method that removes client from the "subscription" list
     * @param client
     */
    @Override
    public void detachObserver(Observer client) {
        clients.remove(client);
        try {
            client.getSocket().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * A method for notifying all the players of certain actions
     * @param command
     */
    @Override
    public void noticeAction(String command) {
        for (Observer client: clients) {
            client.updateObserver(command);
        }
    }


    /**
     * A method that actually executes command and redirects it to the proper handling
     * @param command to execute
     * @param observer client who sended
     */
    public void performAction(String command, Observer observer) {
        List<String> items = Arrays.asList(command.split(" "));
        switch (items.get(0)) {
            case "START" -> {
                try {
                    createGame(items.get(1));
                    noticeAction("STARTED " + items.get(1));
                } catch (creationException e) {
                    observer.updateObserver("FAIL GAME_EXISTS");
                }
                catch (capacityException e) {
                    observer.updateObserver("FAIL TOO_LITTLE_PLAYERS");
                }
            }
            case "MOVE" -> {
                if(observer.getColor().equals(game.getTurn())) {
                    try {
                        noticeAction(game.makeMove(Integer.parseInt(items.get(1)), Integer.parseInt(items.get(2)), Integer.parseInt(items.get(3)), Integer.parseInt(items.get(4)), observer.getColor()));
                    } catch (illegalMoveException e) {
                        observer.updateObserver("FAIL WRONG_MOVE");
                    } catch (GameOverException e) {
                        noticeAction("END " + game.getTurn());
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
