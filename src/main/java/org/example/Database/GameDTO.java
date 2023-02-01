package org.example.Database;

public class GameDTO {
    private int id;
    private String type;

    public GameDTO(String type) {
        this.type = type;
    }

    public GameDTO() {

    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }
}
