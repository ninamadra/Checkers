package org.example.Server;

import org.example.model.Color;

import java.net.Socket;

/**
 * An interface created for client. It enables game controller ability to easy access of sending message to external client
 */
public interface Observer {
    void updateObserver(String message);
    Socket getSocket();
    Color getColor();
}
