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
    public void noticeAboutMove(Observer player, int oldX, int oldY, int newX, int newY) {
        StringBuilder builder = new StringBuilder("MOVED ");
        builder.append(player.getColor().toString());
        builder.append(oldX);
        builder.append(oldY);
        builder.append(newX);
        builder.append(newY);

        for (Observer client: clients) {
            client.updateObserver(builder.toString());
        }
    }

    @Override
    public void noticeAboutPlayersTurn(Observer player) {

    }
}
