package org.example.model.board;

import org.example.model.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassicBoardTest {

    @Test
    void isBoardOK() {
        AbstractBoard board= new ClassicBoard();
        assertSame(board.getFieldAt(0, 0).getColor(), Color.BLACK);
        assertSame(board.getFieldAt(0, 2).getColor(), Color.BLACK);
        assertSame(board.getFieldAt(0, 4).getColor(), Color.BLACK);
        assertSame(board.getFieldAt(0, 6).getColor(), Color.BLACK);
        assertSame(board.getFieldAt(1, 1).getColor(), Color.BLACK);
        assertSame(board.getFieldAt(1, 3).getColor(), Color.BLACK);
        assertSame(board.getFieldAt(1, 5).getColor(), Color.BLACK);
        assertSame(board.getFieldAt(1, 7).getColor(), Color.BLACK);
        assertSame(board.getFieldAt(2, 0).getColor(), Color.BLACK);
        assertSame(board.getFieldAt(2, 2).getColor(), Color.BLACK);
        assertSame(board.getFieldAt(2, 4).getColor(), Color.BLACK);
        assertSame(board.getFieldAt(2, 6).getColor(), Color.BLACK);
        assertSame(board.getFieldAt(3, 1).getColor(), Color.NONE);
        assertSame(board.getFieldAt(3, 3).getColor(), Color.NONE);
        assertSame(board.getFieldAt(3, 5).getColor(), Color.NONE);
        assertSame(board.getFieldAt(3, 7).getColor(), Color.NONE);
        assertSame(board.getFieldAt(4, 0).getColor(), Color.NONE);
        assertSame(board.getFieldAt(4, 2).getColor(), Color.NONE);
        assertSame(board.getFieldAt(4, 4).getColor(), Color.NONE);
        assertSame(board.getFieldAt(4, 6).getColor(), Color.NONE);
        assertSame(board.getFieldAt(5, 1).getColor(), Color.WHITE);
        assertSame(board.getFieldAt(5, 3).getColor(), Color.WHITE);
        assertSame(board.getFieldAt(5, 5).getColor(), Color.WHITE);
        assertSame(board.getFieldAt(5, 7).getColor(), Color.WHITE);
        assertSame(board.getFieldAt(6, 0).getColor(), Color.WHITE);
        assertSame(board.getFieldAt(6, 2).getColor(), Color.WHITE);
        assertSame(board.getFieldAt(6, 4).getColor(), Color.WHITE);
        assertSame(board.getFieldAt(6, 6).getColor(), Color.WHITE);
        assertSame(board.getFieldAt(7, 1).getColor(), Color.WHITE);
        assertSame(board.getFieldAt(7, 3).getColor(), Color.WHITE);
        assertSame(board.getFieldAt(7, 5).getColor(), Color.WHITE);
        assertSame(board.getFieldAt(7, 7).getColor(), Color.WHITE);
    }
}