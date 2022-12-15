package org.example.model.board;

import org.example.model.Color;
import org.example.model.Field;

import java.util.ArrayList;

public abstract class AbstractBoard {

    protected ArrayList<Field> fields;
    public abstract void move(int oldX, int oldY, int newX, int newY, Color color) throws illegalMoveException;

}
