package org.example.Server;

import org.example.Database.DBHandler;
import org.example.Database.GameDBOpertor;
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
    private GameDBOpertor opertor;
    private int savingFlag = 0;

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
        if(game == null) {
            game = new Game();
            game.setVariant(gameType);
        }
        else
            throw new creationException();
        if(clients.size() < 2) {
            attachObserver(new Bot(this));
        }
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

    public void startDB(int id){
        opertor = new GameDBOpertor();
        opertor.Initialize(id);
    }
    public void saveDB(String type) {
        savingFlag = 1;
        opertor = new GameDBOpertor();
        opertor.saveNewGame(type);
    }


    /**
     * A method that actually executes command and redirects it to the proper handling
     * @param command to execute
     * @param observer client who sended
     */
    public void performAction(String command, Observer observer) {
        System.out.println(command);
        List<String> items = Arrays.asList(command.split(" "));
        switch (items.get(0)) {
            case "START" -> {
                try {
                    createGame(items.get(1));
                    if(items.size() == 3 & items.get(2).equals("DB")) {
                        saveDB(items.get(1));
                    }
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
                        String out = game.makeMove(Integer.parseInt(items.get(1)), Integer.parseInt(items.get(2)), Integer.parseInt(items.get(3)), Integer.parseInt(items.get(4)), observer.getColor());
                        noticeAction(out);
                        if(savingFlag == 1) {
                            opertor.saveMove(Integer.parseInt(items.get(1)), Integer.parseInt(items.get(2)), Integer.parseInt(items.get(3)), Integer.parseInt(items.get(4)), observer.getColor().toString());
                        }
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
            case "BOTMOVE" -> {
                if(observer.getColor().equals(game.getTurn())) {
                    try {
                        noticeAction(game.makeBotMove(observer.getColor()));
                    } catch (illegalMoveException e) {
                        throw new RuntimeException(e);
                    } catch (GameOverException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
