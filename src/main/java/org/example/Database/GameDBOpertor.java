package org.example.Database;

import java.util.Comparator;
import java.util.List;

public class GameDBOpertor {
    private DBHandler db;
    private GameDTO game;
    private int counter = 0;
    private List<MoveDTO> moves;

    public GameDBOpertor() {
        db = new DBHandler();
    }
    public void Initialize(int id) {
        List<GameDTO> item = db.getGame(id);
        game = item.get(0);
        moves = db.getMoves(id);
        moves.sort(Comparator.comparing(MoveDTO::getNumber));
        System.out.println(moves.get(0).getNumber());
        System.out.println(moves.get(1).getNumber());
    }

    public String getNext() {
        MoveDTO curr = moves.get(counter);
        counter++;
        return curr.getOldX() + " " + curr.getOldY() + " " + curr.getNewX() + " " + curr.getNewY() ;
    }
}
