package org.example.model.board;

import org.example.model.Color;
import org.example.model.Field;
import org.example.model.rules.DefaultRules;

/**
 *  Implementation of Board with proper ctor (10X10 Board with 4 rows of pieces)
 */
public class PolishBoard extends AbstractBoard {
    /**
     * Ctor adjusted to type, generates fields and adds them to private ArrayList
     */
    public PolishBoard() {
        this.rules = new DefaultRules();
        for (int i = 0; i < 4; i++) {
            int k = i%2;
            for ( int j = k; j < 10; j+=2) {
                fields.add(new Field(i, j, Color.BLACK));
            }
        }
        for (int i = 4; i < 6; i++) {
            int k = i%2;
            for ( int j = k; j < 10; j+=2) {
                fields.add(new Field(i, j, Color.NONE));
            }
        }
        for (int i = 6; i < 10; i++) {
            int k = i%2;
            for ( int j = k; j < 10; j+=2) {
                fields.add(new Field(i, j, Color.WHITE));
            }
        }
    }

    /**
     * @return number of rows in the board
     */
    @Override
    protected int getNoRows() {
        return 10;
    }
}
