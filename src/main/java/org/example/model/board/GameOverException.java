package org.example.model.board;

/**
 * Exception thrown when opposite player has run out of moves/pieces
 */
public class GameOverException extends Exception {
    GameOverException() {
        super();
    }
}