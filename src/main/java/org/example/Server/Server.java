package org.example.Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A class which implements the host server. It listens for clients commands and send back some information
 */
public class Server {
    private static Server instance = null;
    private ServerSocket serverSocket;
    private GameRoom gameRoom;


    public Server() {
    }

    /**
     * Method which implements singleton design pattern
     * @return instance of server
     */
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

    /**
     * A method which is responsible for listing via port and accepting clients
     */
    public void run() {

        try {
            serverSocket = new ServerSocket(2137);
            gameRoom = GameRoom.getInstance();

            while(true) {
                Socket socket = serverSocket.accept();
                Runnable client = new Client(socket, gameRoom);
                ((Client)client).updateObserver("COLOR " + ((Client)client).getColor());
                Thread thread = new Thread(client);
                thread.start();
            }
        }
        catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public GameRoom getGameRoom() {
        return gameRoom;
    }
    public static void main(String[] args) {
        getInstance().run();
    }
}