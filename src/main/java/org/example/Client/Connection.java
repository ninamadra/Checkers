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

/**
 * Class responsible for connection and communication with the game server.
 */
public class Connection {
    private BufferedReader clientInput;
    private PrintWriter clientOutput;
    private Socket clientSocket;
    private GameController gameController;

    /**
     * Constructor connects to server, stabilize communication channel and gets players color
     * @param gc main client controller
     */
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

    /**
     * Method which creates new thread to listen for commands from server
     */
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
                                    gameController.displayMsg("Gra jest już rozpoczęta");
                                }
                                case "TOO_LITTLE_PLAYERS" -> {
                                    gameController.displayMsg("Poczekaj na drugiego gracza");
                                }
                                case "WRONG_MOVE" -> {
                                    gameController.displayMsg("Ruch niepoprawny. Wykonaj go raz jeszcze.");
                                }
                                case "NOT_YOUR_TURN" -> {
                                    gameController.displayMsg("Poczekaj na swoją kolej");
                                }
                            }
                        }
                        case "STARTED" -> {
                            gameController.setType(items.get(1));
                        }
                        case "MOVED" -> {
                            gameController.makeMove(Integer.parseInt(items.get(1)), Integer.parseInt(items.get(2)), Integer.parseInt(items.get(3)), Integer.parseInt(items.get(4)), Color.valueOf(items.get(5)));
                            gameController.displayMsg("Ruch: " + items.get(5));
                        }
                        case "END" -> {
                            if(Color.valueOf(items.get(1)) == gameController.getColor()) {
                                gameController.displayMsg("YOU WIN");
                                gameController.stop();
                            }
                            else{
                                gameController.displayMsg("YOU LOSE");
                                gameController.stop();
                            }

                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    /**
     * Method that sends notification about move to the server
     * @param row
     * @param column
     * @param newRow
     * @param newColumn
     * @param turn
     */
    public void makeMove(int row, int column, int newRow, int newColumn, Color turn) {
        String command = "MOVE " + row + " " + column + " " + newRow + " " + newColumn + " " + turn;
        clientOutput.println(command);
    }

    /**
     * Method that sends request to start a game to the server
     * @param variant
     */
    public void startGame(String variant) {
        String command = "START " + variant;
        clientOutput.println(command);
    }
}