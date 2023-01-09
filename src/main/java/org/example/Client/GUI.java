package org.example.Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.example.Client.Connection.Connection;

import java.io.IOException;
import java.util.Objects;

public class GUI extends Application
{
    @Override
    public void start(final Stage primaryStage) {
//        try {
//            VBox root = new VBox();
//            root.setAlignment(Pos.CENTER);
//            root.setSpacing(10);
//
//            Button btn1=new Button("Join game");
//            Button btn2=new Button("Start game");
//            BorderPane board = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org.example/boardGUI.fxml")));
//
//        Connection con = new Connection();
//
//        root.getChildren().addAll(board);
//        board.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/org.example/Application.css")).toExternalForm());
//
//        root.getChildren().addAll(btn1,btn2);
//        Scene scene = new Scene(root, 300, 250);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//        } catch (IOException e) {
//           e.printStackTrace();
//        }

            GridPane pane = new GridPane();

            // Create 64 rectangles and add to pane
            int count = 0;
            double s = 100; // side of rectangle
            for (int i = 0; i < 8; i++) {
                count++;
                for (int j = 0; j < 8; j++) {
                    Rectangle r = new Rectangle(s, s, s, s);
                    if (count % 2 == 1)
                        r.setFill(Color.WHITE);
                    pane.add(r, j, i);
                    count++;
                }
            }

            // Create a scene and place it in the stage
            Scene scene = new Scene(pane);
            primaryStage.setTitle("java2s.com");
            primaryStage.setScene(scene); // Place in scene in the stage
            primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}