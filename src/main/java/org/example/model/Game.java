package org.example.model;

import org.example.Server.Server;
import org.example.model.board.AbstractBoard;
import org.example.model.board.illegalMoveException;
import org.example.model.factory.AbstractVarFactory;
import org.example.model.factory.ClassicFactory;
import org.example.model.factory.PolishFactory;
import org.example.model.factory.ThaiFactory;
import org.example.model.rules.AbstractRules;

public class Game {
    private AbstractBoard board;
    private AbstractRules rules;

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
        rules = factory.createRules();
        board = factory.createBoard();
    }
    //TODO implement makeMove via part of chain of responsibility
    public String makeMove(int oldX, int oldY, int newX, int newY, Color color) {
       return "FAIL";
    }
}
