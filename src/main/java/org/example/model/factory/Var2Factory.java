package org.example.model.factory;

import org.example.model.board.AbstractBoard;
import org.example.model.board.Var2Board;
import org.example.model.factory.AbstractVarFactory;
import org.example.model.rules.AbstractRules;
import org.example.model.rules.Var2Rules;

public class Var2Factory implements AbstractVarFactory {
    @Override
    public AbstractBoard createBoard() {
        return new Var2Board();
    }

    @Override
    public AbstractRules createRules() {
        return new Var2Rules();
    }
}
