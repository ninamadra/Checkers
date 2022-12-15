package org.example.Server;

public interface GameRoomMediator {
    public void attachObserver(Observer player);
    public void detachObserver(Observer player);
    public void noticeAction(String command);
}
