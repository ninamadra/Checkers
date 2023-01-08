package org.example.Client;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class GUI extends Application
{
    @Override
    public void start(final Stage primaryStage) {

        try {
            VBox root = new VBox();
            //root.setAlignment(Pos.CENTER);
            //root.setSpacing(10);

            //Button btn1=new Button("Join game");
            //Button btn2=new Button("Start game");
            System.out.println(getClass().getResource("boardGUI.fxml"));
            BorderPane board = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("boardGUI.fxml")));

            root.getChildren().addAll(board);
            board.getStylesheets().add(Objects.requireNonNull(getClass().getResource("Application.css")).toExternalForm());
            Scene scene = new Scene(root, 300, 250);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}