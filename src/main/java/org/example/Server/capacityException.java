package org.example.Server;

/**
 * Class for custom exception which is thrown while client tries to start game while he is alone in the server
 */
public class capacityException extends Exception {
    public capacityException() {
        super();
    }
}
