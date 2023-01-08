package org.example.Client;

import javafx.application.Platform;
import org.example.model.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection {
    private BufferedReader clientInput;
    private PrintWriter clientOutput;
    private Socket clientSocket;
    private Color color;
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
    }

    public void listen() {
        new Thread(() -> {
            while(true) {
                try {
                    String command = clientInput.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
