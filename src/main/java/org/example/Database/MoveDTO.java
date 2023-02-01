package org.example.Database;

public class MoveDTO {
    private int gameID;

    private int number;
    private int oldX;
    private int oldY;
    private int newX;
    private int newY;

    public MoveDTO(int gameID, int number, int oldX, int oldY, int newX, int newY) {
        this.gameID = gameID;
        this.number = number;
        this.oldX = oldX;
        this.oldY = oldY;
        this.newX = newX;
        this.newY = newY;
    }
    public MoveDTO() {}

    public int getGameID() {
        return gameID;
    }

    public int getNumber() {
        return number;
    }

    public int getOldX() {
        return oldX;
    }

    public int getOldY() {
        return oldY;
    }

    public int getNewX() {
        return newX;
    }

    public int getNewY() {
        return newY;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public void setOldX(int oldX) {
        this.oldX = oldX;
    }

    public void setOldY(int oldY) {
        this.oldY = oldY;
    }

    public void setNewX(int newX) {
        this.newX = newX;
    }

    public void setNewY(int newY) {
        this.newY = newY;
    }
}
