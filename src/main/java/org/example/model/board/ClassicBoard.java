package org.example.model.board;

import org.example.model.Color;
import org.example.model.Field;
import org.example.model.rules.AbstractRules;


public class ClassicBoard extends AbstractBoard {
    public ClassicBoard() {
        //this.rules = rules;
        for (int i = 0; i < 8; i++) {
            for ( int j = 0; j < 2; j++) {

            }
        }
    }
    @Override
    public void move(int oldX, int oldY, int newX, int newY, Color color) throws illegalMoveException {


    }

}
