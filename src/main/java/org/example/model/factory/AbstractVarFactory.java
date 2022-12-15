package org.example.model.factory;

import org.example.model.board.AbstractBoard;
import org.example.model.rules.AbstractRules;

public interface AbstractVarFactory {
    AbstractBoard createBoard();
    AbstractRules createRules();
}
