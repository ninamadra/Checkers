package org.example.Server;

import org.example.model.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * A class which represents single client. It runs its own thread to listen for commands
 */
public class Client implements Observer, Runnable {
    private final Socket socket;
    private final BufferedReader input;
    private final PrintWriter output;
    private final GameRoom gameRoom;
    private final Color color;

    /**
     * @param socket
     * @param gameRoom instance of class which is controller for whole game
     * @throws IOException
     * Basic constructor of object
     */
    public Client(Socket socket, GameRoom gameRoom) throws IOException {
        this.socket = socket;
        this.gameRoom = gameRoom;
        this.gameRoom.attachObserver(this);
        input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        output = new PrintWriter(this.socket.getOutputStream(), true);
        color = gameRoom.getColorToSet();
    }

    /**
     * @param message
     * A method for sending message outside of server
     */
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

    /**
     * A method for listing outside of server. It also detaches observer in case of getting IOException
     */
    @Override
    public void run() {
        while(true) {

            try {
                String command = input.readLine();
                System.out.println(command);
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
