package org.example.Client.Connection;

import javafx.application.Platform;
import org.example.Client.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection {
    private BufferedReader clientInput;
    private PrintWriter clientOutput;
    private Socket clientSocket;
    private Game game;
    public Connection() {
        try {
            clientSocket = new Socket("localhost",2137);
        } catch (IOException e) {
            Platform.exit();
            return;
        }
        try {
            clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {

        }
        try {
            clientOutput = new PrintWriter(clientSocket.getOutputStream(),true);
        } catch (IOException e) {

        }
        game = new Game();
        try {
            game.setClientColor(clientInput.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void listen() {
        new Thread(() -> {
            while(true) {
                String command;
                try {
                    command = clientInput.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if(command.startsWith("MOVED ")) {
                    // TODO: 08.01.2023 implementacja ruchu na planszy 
                }
            }
        }).start();
    }
}
