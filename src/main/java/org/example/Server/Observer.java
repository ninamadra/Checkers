package org.example.Server;

import java.net.Socket;

public interface Observer {
    void updateObserver(String message);
    Socket getSocket();
}
