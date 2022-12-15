package org.example.Server;

import org.example.model.board.AbstractBoard;
import org.example.model.factory.AbstractVarFactory;
import org.example.model.factory.ClassicFactory;
import org.example.model.factory.PolishFactory;
import org.example.model.factory.ThaiFactory;
import org.example.model.rules.AbstractRules;

public class GameRoom {
    private AbstractBoard board;
    private AbstractRules rules;
    private GameRoomController gameRoomController;

    public GameRoom(GameRoomController gameRoomController) {
        this.gameRoomController = gameRoomController;
    }

    public void setVariant(String variant) {
        AbstractVarFactory factory = null;
        switch (variant) {
            case "CLASSIC":
                factory = new ClassicFactory();
                break;
            case "THAI":
                factory = new ThaiFactory();
                break;
            case "POLISH":
                factory = new PolishFactory();
                break;
        }
        board = factory.createBoard();
        rules = factory.createRules();
    }
}
