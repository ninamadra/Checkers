package org.example.model.factory;

import org.example.model.board.AbstractBoard;
import org.example.model.board.PolishBoard;
import org.example.model.rules.AbstractRules;
import org.example.model.rules.PolishRules;

public class PolishFactory implements AbstractVarFactory {
    @Override
    public AbstractBoard createBoard() {
        return new PolishBoard();
    }

    @Override
    public AbstractRules createRules() {
        return new PolishRules();
    }
}
