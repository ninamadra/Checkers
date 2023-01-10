package org.example.Client;

import javafx.application.Platform;
import org.example.Client.Game;
import org.example.model.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class Connection {
    private BufferedReader clientInput;
    private PrintWriter clientOutput;
    private Socket clientSocket;
    private GameController gameController;
    public Connection(GameController gc) {
        try {
            clientSocket = new Socket("localhost",2137);
        } catch (IOException e) {
            Platform.exit();
            return;
        }
        try {
            clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            Platform.exit();
            return;
        }
        try {
            clientOutput = new PrintWriter(clientSocket.getOutputStream(),true);
        } catch (IOException e) {
            Platform.exit();
            return;
        }
        this.gameController = gc;
        try {
            gameController.setColor(clientInput.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        listen();
    }

    public void listen() {
        new Thread(() -> {
            while(true) {
                try {
                    String command = clientInput.readLine();
                    System.out.println(command);
                    List<String> items = Arrays.asList(command.split(" "));
                    switch(items.get(0)) {
                        case "FAIL" -> {
                            switch (items.get(1)) {
                                case "GAME_EXISTS" -> {
                                    // TODO: 09.01.2023 zobaczymy czy potrzebne
                                }
                                case "TOO_LITTLE_PLAYERS" -> {
                                    // TODO: 09.01.2023 informuj GUI, wyswietlnie komunikatu
                                    Platform.exit();
                                }
                                case "WRONG_MOVE" -> {

                                }
                                case "NOT_YOUR_TURN" -> {

                                }
                            }
                        }
                        case "STARTED" -> {
                            gameController.setType(items.get(1));
                        }
                        case "MOVED" -> {
                            // TODO: 08.01.2023 implementacja ruchu na planszy
                        }
                        case "END" -> {
                            if(Color.valueOf(items.get(1)) == gameController.getColor()) {
                                // TODO: 09.01.2023 win
                            }
                            else{
                                // TODO: 09.01.2023 lose
                            }

                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public void makeMove(int row, int column, int newRow, int newColumn, Color turn) {
        String command = "MOVE " + row + " " + column + " " + newRow + " " + newColumn + " " + turn;
        clientOutput.println(command);
    }

    public void startGame(String variant) {
        String command = "START " + variant;
        clientOutput.println(command);
    }
}