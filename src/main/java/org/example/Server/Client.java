package org.example.Server;

import org.example.model.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//TODO implement runnable and getting input
public class Client implements Observer {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private GameRoom gameRoom;
    private Color color;

    public Client(Socket socket, GameRoom gameRoom) throws IOException {
        this.socket = socket;
        this.gameRoom = gameRoom;
        this.gameRoom.attachObserver(this);
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

    @Override
    public Color getColor() {
        return color;
    }
}
