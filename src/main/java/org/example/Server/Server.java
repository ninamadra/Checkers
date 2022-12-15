package org.example.Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static Server instance = null;
    private ServerSocket serverSocket;
    private GameRoomController gameRoomController;


    public Server() {
    }

    public static Server getInstance() {
        if(instance == null) {
            synchronized (Server.class) {
                if(instance == null) {
                    instance = new Server();
                }
            }
        }

        return instance;
    }

    public void run() throws InterruptedException {

        try {
            serverSocket = new ServerSocket(2137);
            gameRoomController = new GameRoomController();

            while(true) {
                Socket socket = serverSocket.accept();
            }
        }
        catch(IOException ex) {}
    }

    public static void main(String[] args) throws InterruptedException {
        getInstance().run();
    }
}