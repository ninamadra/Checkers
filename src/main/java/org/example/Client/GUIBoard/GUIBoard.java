package org.example.Client.GUIBoard;

import org.example.Client.Square;
import org.example.model.Color;

/**
 * Interface which ensures that method for gui will be provided
 */
public interface GUIBoard  {
    void updateBoard(int oldX, int oldY, int newX, int newY, Color color);
    void respondToClick(Square square);
}
