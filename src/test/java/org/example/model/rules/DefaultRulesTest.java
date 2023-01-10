package org.example.model.rules;

import org.example.model.Color;
import org.example.model.Field;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DefaultRulesTest {

    @Test
    void isMoveValid() {
        DefaultRules rules = new DefaultRules();
        Field oldField = new Field(0, 0, Color.BLACK);
        Field newField = new Field(2, 2, Color.NONE);
        ArrayList<Field> capturedFields = new ArrayList<>();

        //null field
        assertFalse(rules.isMoveValid(null, oldField, capturedFields, Color.BLACK));

        //not your piece
        assertFalse(rules.isMoveValid(oldField, newField, capturedFields, Color.WHITE));

        newField.setColor(Color.WHITE);
        //newField already taken
        assertFalse(rules.isMoveValid(oldField, newField, capturedFields, Color.BLACK));

        newField.setColor(Color.NONE);
        Field newField2 = new Field(1, 1, Color.NONE);
        //normal move
        assertTrue(rules.isMoveValid(oldField, newField2, capturedFields, Color.BLACK));

        capturedFields.add(newField2);
        //not in normal piece range nor capture
        assertFalse(rules.isMoveValid(oldField, newField, capturedFields, Color.BLACK));

        newField2.setColor(Color.WHITE);
        //Capture range
        assertTrue(rules.isMoveValid(oldField, newField, capturedFields, Color.BLACK));

        Field newField3 = new Field(3,3,Color.NONE);
        capturedFields.add(newField);
        assertFalse(rules.isMoveValid(oldField, newField3, capturedFields, Color.BLACK));

        //king's capture
        oldField.setIsKing(true);
        assertTrue(rules.isMoveValid(oldField, newField3, capturedFields, Color.BLACK));

        newField2.setColor(Color.NONE);
        //king's casual move
        assertTrue(rules.isMoveValid(oldField, newField3, capturedFields, Color.BLACK));

        newField2.setColor(Color.BLACK);
        //attempt to capture your own pieces
        assertFalse(rules.isMoveValid(oldField, newField3, capturedFields, Color.BLACK));

        newField.setColor(Color.WHITE);
        newField2.setColor(Color.WHITE);
        //attempt to multi-capture without space between
        assertFalse(rules.isMoveValid(oldField, newField3, capturedFields, Color.BLACK));

    }

    @Test
    void isInRange() {

        DefaultRules rules = new DefaultRules();
        Field initialField = new Field(1, 1, Color.BLACK);
        Field field1 = new Field(0, 0, Color.NONE);
        Field field2 = new Field(2, 0, Color.NONE);
        Field field3 = new Field(0, 2, Color.NONE);
        Field field4 = new Field(2, 2, Color.NONE);

        //black pieces
        assertFalse(rules.isInRange(initialField, field1, initialField.getColor()));
        assertFalse(rules.isInRange(initialField, field3, initialField.getColor()));
        assertTrue(rules.isInRange(initialField, field2, initialField.getColor()));
        assertTrue(rules.isInRange(initialField, field4, initialField.getColor()));

        initialField.setColor(Color.WHITE);
        //white pieces
        assertFalse(rules.isInRange(initialField, field2, initialField.getColor()));
        assertFalse(rules.isInRange(initialField, field4, initialField.getColor()));
        assertTrue(rules.isInRange(initialField, field1, initialField.getColor()));
        assertTrue(rules.isInRange(initialField, field3, initialField.getColor()));
    }

    @Test
    void isInCaptureRange() {

        DefaultRules rules = new DefaultRules();
        Field initialField = new Field(2, 2, Color.BLACK);
        Field field1 = new Field(0, 0, Color.NONE);
        Field field2 = new Field(4, 0, Color.NONE);
        Field field3 = new Field(0, 4, Color.NONE);
        Field field4 = new Field(4, 4, Color.NONE);
        Field fieldNotInRange = new Field(5, 5, Color.NONE);

        assertTrue(rules.isInCaptureRange(initialField, field1));
        assertTrue(rules.isInCaptureRange(initialField, field2));
        assertTrue(rules.isInCaptureRange(initialField, field3));
        assertTrue(rules.isInCaptureRange(initialField, field4));
        assertFalse(rules.isInCaptureRange(initialField, fieldNotInRange));
    }

    @Test
    void isInKingRange() {

        DefaultRules rules = new DefaultRules();
        Field initialField = new Field(4, 4, Color.BLACK);
        Field field1 = new Field(2, 6, Color.NONE);
        Field field2 = new Field(1, 1, Color.NONE);
        Field field3 = new Field(7, 7, Color.NONE);
        Field field4 = new Field(7, 1, Color.NONE);
        Field field5 = new Field(5, 3, Color.NONE);
        Field fieldNotInRange = new Field(7, 5, Color.NONE);
        Field fieldNotInRange2 = new Field(0, 4, Color.NONE);

        assertTrue(rules.isInKingRange(initialField, field1));
        assertTrue(rules.isInKingRange(initialField, field2));
        assertTrue(rules.isInKingRange(initialField, field3));
        assertTrue(rules.isInKingRange(initialField, field4));
        assertTrue(rules.isInKingRange(initialField, field5));
        assertFalse(rules.isInKingRange(initialField, fieldNotInRange));
        assertFalse(rules.isInKingRange(initialField, fieldNotInRange2));
    }

    @Test
    void isInKingCaptureRange() {

        DefaultRules rules = new DefaultRules();
        Field initialField = new Field(4, 4, Color.BLACK);
        Field field1 = new Field(2, 6, Color.NONE);
        Field field2 = new Field(1, 1, Color.NONE);
        Field field3 = new Field(7, 7, Color.NONE);
        Field field4 = new Field(7, 1, Color.NONE);
        Field field5 = new Field(5, 3, Color.NONE);
        Field fieldNotInRange = new Field(7, 5, Color.NONE);
        Field fieldNotInRange2 = new Field(0, 4, Color.NONE);

        assertTrue(rules.isInKingRange(initialField, field1));
        assertTrue(rules.isInKingRange(initialField, field2));
        assertTrue(rules.isInKingRange(initialField, field3));
        assertTrue(rules.isInKingRange(initialField, field4));
        assertTrue(rules.isInKingRange(initialField, field5));
        assertFalse(rules.isInKingRange(initialField, fieldNotInRange));
        assertFalse(rules.isInKingRange(initialField, fieldNotInRange2));

    }
}
