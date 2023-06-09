package org.example.Database;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GameDBOpertor {
    private DBHandler db;
    private GameDTO game;
    private int counter = 1;
    private List<MoveDTO> moves;

    public GameDBOpertor() {
        db = new DBHandler();
    }
    public void Initialize(int id) {
        List<GameDTO> item = db.getGame(id);
        game = item.get(id);
        moves = db.getMoves(id);
        moves.sort(Comparator.comparing(MoveDTO::getNumber));
    }

    public void saveMove(int oldX, int oldY, int newX, int newY, String color) {
        db.addMove(game.getId(), counter, oldX, oldY, newX, newY, color);
        counter++;
    }

    public void saveNewGame(String type) {
        int id = db.addGame(type);
        game = db.getGame(id).get(0);
        System.out.println(game.getId()+game.getType());
    }

    public String getType() {
        return game.getType();
    }

    public MoveDTO getNext() {
        return moves.get(counter);
    }
}
