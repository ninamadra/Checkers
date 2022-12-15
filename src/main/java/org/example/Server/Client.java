package org.example.Server;

import java.net.Socket;

public class Client implements Observer {
    private Socket socket;
    @Override
    public void updateObserver(String message) {

    }

    @Override
    public Socket getSocket() {
        return socket;
    }
}
