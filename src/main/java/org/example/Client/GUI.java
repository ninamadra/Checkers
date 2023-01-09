package org.example.Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.model.board.AbstractBoard;
import org.example.model.board.ClassicBoard;

import java.io.IOException;
import java.util.Objects;

public class GUI extends Application
{
    AbstractGUIBoard board;
    GameController gameController;
    @Override
    public void start(final Stage primaryStage) {
        gameController = new GameController();
        

        BorderPane root = new BorderPane();
        MyMenuItem classic = new MyMenuItem("classic");
        MyMenuItem thai = new MyMenuItem("thai");
        MyMenuItem polish = new MyMenuItem("polish");
        Menu type = new Menu("type");
        type.getItems().addAll(classic, thai, polish);
        MenuBar menuBar = new MenuBar(type);
        board = new ClassicGUIBoard();
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