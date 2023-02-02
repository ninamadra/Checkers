package org.example.Database;

import java.util.ArrayList;
import java.util.List;

public class GameDTO {
    private int id;
    private String type;
    private List<MoveDTO> moves;

    public GameDTO(String type) {
        this.type = type;
        moves = new ArrayList<>();
    }

    public GameDTO() {
        moves = new ArrayList<>();
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

    public List<MoveDTO> getMoves() {
        return moves;
    }

    public void setMoves(List<MoveDTO> moves) {
        this.moves = moves;
    }
}
