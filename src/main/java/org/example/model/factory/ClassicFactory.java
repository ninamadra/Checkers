package org.example.model.factory;

import org.example.model.board.AbstractBoard;
import org.example.model.board.ClassicBoard;
import org.example.model.rules.AbstractRules;
import org.example.model.rules.ClassicRules;

public class ClassicFactory implements AbstractVarFactory {
    @Override
    public AbstractBoard createBoard() {
        return new ClassicBoard();
    }

    @Override
    public AbstractRules createRules() {
        return new ClassicRules();
    }
}
