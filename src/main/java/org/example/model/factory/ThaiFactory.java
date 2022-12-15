package org.example.model.factory;

import org.example.model.board.AbstractBoard;
import org.example.model.board.ThaiBoard;
import org.example.model.rules.AbstractRules;
import org.example.model.rules.ThaiRules;

public class ThaiFactory implements AbstractVarFactory {
    @Override
    public AbstractBoard createBoard() {
        return new ThaiBoard();
    }

    @Override
    public AbstractRules createRules() {
        return new ThaiRules();
    }
}
