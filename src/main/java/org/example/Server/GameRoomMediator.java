package org.example.Server;

public interface GameRoomMediator {
    public void attachObserver(Observer player);
    public void detachObserver(Observer player);
    public void noticeAboutMove(Observer player, int oldX, int oldY, int newX, int newY);
    public void noticeAboutPlayersTurn(Observer player);
}
