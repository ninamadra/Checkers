package org.example.Database;

import org.example.model.Color;

public class MoveDTO {
    private  int ID;
    private int gameID;

    private int number;
    private int oldX;
    private int oldY;
    private int newX;
    private int newY;
    private String color;

    public MoveDTO(int gameID, int number, int oldX, int oldY, int newX, int newY, String color) {
        this.gameID = gameID;
        this.number = number;
        this.oldX = oldX;
        this.oldY = oldY;
        this.newX = newX;
        this.newY = newY;
        this.color = color;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
