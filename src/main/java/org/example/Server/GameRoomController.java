package org.example.Server;

import java.io.IOException;
import java.util.ArrayList;

public class GameRoomController implements GameRoomMediator{
    GameRoom gameRoom;
    ArrayList<Observer> clients;

    public GameRoomController() {
        clients = new ArrayList<>();
    }

    @Override
    public void attachObserver(Observer player) {
        clients.add(player);
    }

    @Override
    public void detachObserver(Observer player) {
        clients.remove(player);
        try {
            player.getSocket().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void noticeAboutMove(Observer player, int oldX, int oldRow, int newX, int newRow) {

    }

    @Override
    public void noticeAboutPlayersTurn(Observer player) {

    }
}
