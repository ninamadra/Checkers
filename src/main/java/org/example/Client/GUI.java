package org.example.Client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.Client.GUIBoard.AbstractGUIBoard;
import org.example.Client.GUIBoard.ClassicGUIBoard;
import org.example.Client.GUIBoard.PolishGUIBoard;
import org.example.Client.GUIBoard.ThaiGUIBoard;

public class GUI extends Application
{
    AbstractGUIBoard board;
    GameController gameController;
    BorderPane root;
    MenuItem classic = new MenuItem("classic");
    MenuItem thai = new MenuItem("thai");
    MenuItem polish = new MenuItem("polish");
    @Override
    public void start(final Stage primaryStage) {
        gameController = new GameController(this);
        root = new BorderPane();
        board = null;

        classic.setOnAction(e -> {
            gameController.startGame("CLASSIC");
        });
        thai.setOnAction(e -> {
            gameController.startGame("THAI");
        });
        polish.setOnAction(e -> {
            gameController.startGame("POLISH");

//            board.updateBoard(6,2,5,3,Color.WHITE);
//            board.updateBoard(6,6,4,4,Color.WHITE);
//            board.updateBoard(3,5,6,2,Color.BLACK);
//            board.updateBoard(6,2,9,5,Color.BLACK);
//            board.updateBoard(9,5,8,4,Color.BLACK);
//            board.updateBoard(8,4,5,7,Color.BLACK);
        });
        Menu type = new Menu("type");
        type.getItems().addAll(classic, thai, polish);
        MenuBar menuBar = new MenuBar(type);
        root.setTop(menuBar);
        root.setCenter(board);
        Scene scene = new Scene(root, 800, 825);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println(gameController.getColor());
    }

    public void setType(String var) {
        switch(var) {
            case "THAI" -> board = new ThaiGUIBoard(gameController);
            case "CLASSIC" -> board = new ClassicGUIBoard(gameController);
            case "POLISH" -> board = new PolishGUIBoard(gameController);
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                root.setCenter(board);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }


}