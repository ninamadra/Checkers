package org.example.model.factory;

import org.example.model.board.AbstractBoard;
import org.example.model.board.Var3Board;
import org.example.model.factory.AbstractVarFactory;
import org.example.model.rules.AbstractRules;
import org.example.model.rules.Var3Rules;

public class Var3Factory implements AbstractVarFactory {
    @Override
    public AbstractBoard createBoard() {
        return new Var3Board();
    }

    @Override
    public AbstractRules createRules() {
        return new Var3Rules();
    }
}
