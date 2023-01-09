package org.example.Client;

import javafx.application.Application;
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
    @Override
    public void start(final Stage primaryStage) {
        gameController = new GameController();

        BorderPane root = new BorderPane();
        MenuItem classic = new MenuItem("classic");
        classic.setOnAction(e -> {
            board = new ClassicGUIBoard();
        });
        MenuItem thai = new MenuItem("thai");
        thai.setOnAction(e -> {
            board = new ThaiGUIBoard();
        });
        MenuItem polish = new MenuItem("polish");
        polish.setOnAction(e -> {
            board = new PolishGUIBoard();
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

    public static void main(String[] args) {
        launch(args);
    }
}