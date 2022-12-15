package org.example.model.factory;

import org.example.model.board.AbstractBoard;
import org.example.model.board.Var1Board;
import org.example.model.factory.AbstractVarFactory;
import org.example.model.rules.AbstractRules;
import org.example.model.rules.Var1Rules;

public class Var1Factory implements AbstractVarFactory {
    @Override
    public AbstractBoard createBoard() {
        return new Var1Board();
    }

    @Override
    public AbstractRules createRules() {
        return new Var1Rules();
    }
}
