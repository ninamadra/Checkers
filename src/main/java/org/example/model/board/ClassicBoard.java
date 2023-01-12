package org.example.model.board;

import org.example.model.Color;
import org.example.model.Field;
import org.example.model.rules.DefaultRules;

/**
 * Implementation of Board with proper ctor (8X8 Board with 3 rows of pieces)
 */
public class ClassicBoard extends AbstractBoard {
    /**
     * Ctor adjusted to type, generates fields and adds them to private ArrayList
     */
    public ClassicBoard() {
        this.rules = new DefaultRules();
        for (int i = 0; i < 3; i++) {
            int k = i%2;
            for ( int j = k; j < 8; j+=2) {
                fields.add(new Field(i, j, Color.BLACK));
            }
        }
        for (int i = 3; i < 5; i++) {
            int k = i%2;
            for ( int j = k; j < 8; j+=2) {
                fields.add(new Field(i, j, Color.NONE));
            }
        }
        for (int i = 5; i < 8; i++) {
            int k = i%2;
            for ( int j = k; j < 8; j+=2) {
                fields.add(new Field(i, j, Color.WHITE));
            }
        }
    }

    /**
     * @return number of rows in the board
     */
    @Override
    protected int getNoRows() {
        return 8;
    }
}
