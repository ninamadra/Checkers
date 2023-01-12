package org.example.Server;

/**
 * An interface created for easy communication between clients and server
 */
public interface GameRoomMediator {
    public void attachObserver(Observer player);
    public void detachObserver(Observer player);
    public void noticeAction(String command);
}
