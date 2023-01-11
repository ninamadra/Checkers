package org.example.model.board;

import org.example.model.Color;
import org.example.model.Field;
import org.example.model.rules.DefaultRules;

public class PolishBoard extends AbstractBoard {
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
    @Override
    protected int getNoRows() {
        return 10;
    }
}
