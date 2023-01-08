package org.example.model.board;

import org.example.model.Color;
import org.example.model.Field;
import org.example.model.rules.DefaultRules;

public class ThaiBoard extends AbstractBoard {
    public ThaiBoard() {
        this.rules = new DefaultRules();
        for (int i = 0; i < 2; i++) {
            int k = i%2;
            for ( int j = k; j < 8; j+=2) {
                fields.add(new Field(i, j, Color.WHITE));
            }
        }
        for (int i = 2; i < 6; i++) {
            int k = i%2;
            for ( int j = k; j < 8; j+=2) {
                fields.add(new Field(i, j, Color.NONE));
            }
        }
        for (int i = 6; i < 8; i++) {
            int k = i%2;
            for ( int j = k; j < 8; j+=2) {
                fields.add(new Field(i, j, Color.BLACK));
            }
        }
    }
}
