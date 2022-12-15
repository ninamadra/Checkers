package org.example.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//TODO implement runnnable
public class Client implements Observer {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private GameRoomController gameRoomController;

    public Client(Socket socket, GameRoomController gameRoomController) throws IOException {
        this.socket = socket;
        this.gameRoomController = gameRoomController;
        input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        output = new PrintWriter(this.socket.getOutputStream(), true);
    }
    @Override
    public void updateObserver(String message) {
        output.println(message);
    }

    @Override
    public Socket getSocket() {
        return socket;
    }
}
