package org.example.model;

import org.example.model.board.AbstractBoard;
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
        board = factory.createBoard();
        rules = factory.createRules();
    }
    //TODO implement makeMove via part of chain of responsibility
    public void makeMove() {

    }
}
