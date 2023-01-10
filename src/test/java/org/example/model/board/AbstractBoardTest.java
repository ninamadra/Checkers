package org.example.model.board;

import org.example.model.Color;
import org.example.model.Field;
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
    void findObligatoryMoves() {
    }

    @Test
    void isCapturePossible() {
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
        assertTrue(board.isGameOver(Color.BLACK));

        //pieces with possible moves
        board.getFieldAt(7,1).setColor(Color.WHITE);
        assertFalse(board.isGameOver(Color.BLACK));

        //pieces without possible moves
        board.getFieldAt(6,0).setColor(Color.BLACK);
        board.getFieldAt(6,2).setColor(Color.BLACK);
        board.getFieldAt(5,3).setColor(Color.BLACK);
        assertTrue(board.isGameOver(Color.BLACK));

    }

    @Test
    void isAnotherMovePossible() {
        
    }
}