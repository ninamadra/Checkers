package org.example.model.board;

import org.example.model.Color;
import org.example.model.Field;
import org.example.model.rules.DefaultRules;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AbstractBoardTest {

    @Test
    void move() {
    }

    @Test
    void findCapturedFields() {
        ClassicBoard board = new ClassicBoard();
        Field oldField = board.getFieldAt(0, 0);
        Field newField = board.getFieldAt(3,3);
        Field capturedField = board.getFieldAt(1,1);
        Field capturedField2 = board.getFieldAt(2,2);
        ArrayList<Field> capturedFields = new ArrayList<>();
        capturedFields.add(capturedField);
        capturedFields.add(capturedField2);

        assertEquals(capturedFields, board.findCapturedFields(oldField, newField));

        oldField= board.getFieldAt(7,3);
        newField = board.getFieldAt(3,7);
        capturedField = board.getFieldAt(6,4);
        capturedField2 = board.getFieldAt(5, 5);
        Field capturedField3 = board.getFieldAt(4, 6);
        ArrayList<Field> captured = new ArrayList<>();
        captured.add(capturedField3);
        captured.add(capturedField2);
        captured.add(capturedField);

        assertEquals(captured, board.findCapturedFields(oldField, newField));

    }

    @Test
    void isCapturePossible() {
        ClassicBoard board = new ClassicBoard();
        //no captures
        assertFalse(board.isCapturePossible(board.getFieldAt(2,4), Color.BLACK));

        //black captures
        board.getFieldAt(3,3).setColor(Color.WHITE);
        assertTrue(board.isCapturePossible(board.getFieldAt(2,4), Color.BLACK));

        //white captures
        board.getFieldAt(4,6).setColor(Color.BLACK);
        assertTrue(board.isCapturePossible(board.getFieldAt(5,7), Color.WHITE));

        //black king captures


        //white king captures


    }

    @Test
    void isGameOver() {
        ClassicBoard board = new ClassicBoard();
        ArrayList<Field> fields = board.getFields();

        //no remaining pieces
        for (Field field:fields) {
            if(field.getColor() == Color.WHITE) {
                field.setColor(Color.NONE);
            }
        }
        assertTrue(board.isGameOver(Color.WHITE));

        //pieces with possible moves
        board.getFieldAt(7,3).setColor(Color.WHITE);
        assertFalse(board.isGameOver(Color.WHITE));

        //pieces without possible moves
        board.getFieldAt(6,2).setColor(Color.BLACK);
        board.getFieldAt(6,4).setColor(Color.BLACK);
        board.getFieldAt(5,1).setColor(Color.BLACK);
        board.getFieldAt(5,5).setColor(Color.BLACK);
        assertTrue(board.isGameOver(Color.WHITE));

        //advanced position without moves
        for (Field f:fields) {
            f.setColor(Color.NONE);
        }
        board.getFieldAt(6,0).setColor(Color.WHITE);
        board.getFieldAt(1,1).setColor(Color.BLACK);
        board.getFieldAt(5,1).setColor(Color.BLACK);
        board.getFieldAt(2,2).setColor(Color.BLACK);
        board.getFieldAt(4,2).setColor(Color.BLACK);
        board.getFieldAt(3,3).setColor(Color.WHITE);
        board.getFieldAt(2,4).setColor(Color.BLACK);
        board.getFieldAt(1,5).setColor(Color.BLACK);
        board.getFieldAt(3,5).setColor(Color.BLACK);
        board.getFieldAt(0,6).setColor(Color.WHITE);
        board.getFieldAt(0,6).setIsKing(true);
        board.getFieldAt(4,6).setColor(Color.BLACK);
        board.getFieldAt(1,7).setColor(Color.BLACK);
        board.getFieldAt(5,7).setColor(Color.WHITE);
        assertTrue(board.isGameOver(Color.WHITE));

    }

    @Test
    void isAnotherMovePossible() {
        ClassicBoard board = new ClassicBoard();
        //forward moves only
        assertFalse(board.isAnotherMovePossible(board.getFieldAt(0,0), Color.BLACK));
        assertFalse(board.isAnotherMovePossible(board.getFieldAt(7,3), Color.WHITE));
        assertTrue(board.isAnotherMovePossible(board.getFieldAt(2,0), Color.BLACK));
        assertTrue(board.isAnotherMovePossible(board.getFieldAt(2,4), Color.BLACK));
        assertTrue(board.isAnotherMovePossible(board.getFieldAt(2,2), Color.BLACK));

        board.getFieldAt(3,3).setColor(Color.WHITE);

        Field field = board.getFieldAt(2,4);
        assertSame(field.getColor(), Color.BLACK);
        assertTrue(board.isAnotherMovePossible(board.getFieldAt(2,4), Color.BLACK));

        board.getFieldAt(4,2).setColor(Color.WHITE);
        board.getFieldAt(3,5).setColor(Color.BLACK);
        assertFalse(board.isAnotherMovePossible(board.getFieldAt(2,4), Color.BLACK));

        ArrayList<Field> fields = board.getFields();
        for (Field f:fields) {
            f.setColor(Color.NONE);
        }
        board.getFieldAt(6,0).setColor(Color.WHITE);
        board.getFieldAt(1,1).setColor(Color.BLACK);
        board.getFieldAt(5,1).setColor(Color.BLACK);
        board.getFieldAt(2,2).setColor(Color.BLACK);
        board.getFieldAt(4,2).setColor(Color.BLACK);
        board.getFieldAt(3,3).setColor(Color.WHITE);
        board.getFieldAt(2,4).setColor(Color.BLACK);
        board.getFieldAt(1,5).setColor(Color.BLACK);
        board.getFieldAt(3,5).setColor(Color.BLACK);
        board.getFieldAt(0,6).setColor(Color.WHITE);
        board.getFieldAt(0,6).setIsKing(true);
        board.getFieldAt(4,6).setColor(Color.BLACK);
        board.getFieldAt(1,7).setColor(Color.BLACK);
        board.getFieldAt(5,7).setColor(Color.WHITE);

        assertFalse(board.isAnotherMovePossible(board.getFieldAt(6,0), Color.WHITE));
        assertFalse(board.isAnotherMovePossible(board.getFieldAt(3,3), Color.WHITE));
        assertFalse(board.isAnotherMovePossible(board.getFieldAt(0,6), Color.WHITE));

        //checking possible moves if piece is a king
        board.getFieldAt(2,6).setColor(Color.BLACK);
        board.getFieldAt(4,4).setColor(Color.WHITE);
        assertFalse(board.isAnotherMovePossible(board.getFieldAt(4,4), Color.WHITE));
        board.getFieldAt(4,4).setIsKing(true);
        assertTrue(board.isAnotherMovePossible(board.getFieldAt(4,4), Color.WHITE));

    }
}