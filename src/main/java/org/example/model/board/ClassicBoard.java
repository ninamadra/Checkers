package org.example.model.board;

import org.example.model.Color;
import org.example.model.Field;
import org.example.model.rules.DefaultRules;

public class ClassicBoard extends AbstractBoard {
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

}
