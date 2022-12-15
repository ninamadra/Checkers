package org.example.Server;

import org.example.model.Color;

import java.net.Socket;

public interface Observer {
    void updateObserver(String message);
    Socket getSocket();
    Color getColor();
}
