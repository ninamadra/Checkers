package org.example.Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static Server instance = null;
    private ServerSocket serverSocket;
    private GameRoom gameRoom;


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

    public void run() {

        try {
            serverSocket = new ServerSocket(2137);
            gameRoom = GameRoom.getInstance();

            while(true) {
                Socket socket = serverSocket.accept();
                Client client = new Client(socket, gameRoom);
                client.updateObserver("COLOR " + client.getColor());
            }
        }
        catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args) {
        getInstance().run();
    }
}