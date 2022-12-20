package org.example.Server;

import org.example.model.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Observer, Runnable {
    private final Socket socket;
    private final BufferedReader input;
    private final PrintWriter output;
    private final GameRoom gameRoom;
    private final Color color;

    public Client(Socket socket, GameRoom gameRoom) throws IOException {
        this.socket = socket;
        this.gameRoom = gameRoom;
        this.gameRoom.attachObserver(this);
        input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        output = new PrintWriter(this.socket.getOutputStream(), true);
        color = gameRoom.getColorToSet();
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

    @Override
    public void run() {
        while(true) {

            try {
                String command = input.readLine();
                gameRoom.performAction(command, this);
            }
            catch (IOException e) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                gameRoom.detachObserver(this);
            }
        }
    }
}
