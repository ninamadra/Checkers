package org.example.Client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.Client.GUIBoard.AbstractGUIBoard;
import org.example.Client.GUIBoard.ClassicGUIBoard;
import org.example.Client.GUIBoard.PolishGUIBoard;
import org.example.Client.GUIBoard.ThaiGUIBoard;
import org.example.model.Color;

public class GUI extends Application
{
    AbstractGUIBoard board;
    GameController gameController;
    BorderPane root;
    MenuItem classic = new MenuItem("classic");
    MenuItem thai = new MenuItem("thai");
    MenuItem polish = new MenuItem("polish");
    Label label;
    @Override
    public void start(final Stage primaryStage) {
        gameController = new GameController(this);
        root = new BorderPane();
        label = new Label("" + gameController.getColor());

        classic.setOnAction(e -> gameController.startGame("CLASSIC"));
        thai.setOnAction(e -> gameController.startGame("THAI"));
        polish.setOnAction(e -> { gameController.startGame("POLISH"); });
        HBox top = new HBox();
        Menu type = new Menu("type");
        type.getItems().addAll(classic, thai, polish);
        MenuBar menuBar = new MenuBar(type);
        top.getChildren().add(0, menuBar);
        top.getChildren().add(1, label);
        top.setSpacing(10);
        top.setAlignment(Pos.CENTER_LEFT);
        root.setTop(top);
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
            case "POLISH" -> {board = new PolishGUIBoard(gameController);
//            board.updateBoard(6,2,5,3,Color.WHITE);
//            board.updateBoard(6,6,4,4,Color.WHITE);
//            board.updateBoard(3,5,6,2,Color.BLACK);
//            board.updateBoard(6,2,9,5,Color.BLACK);
//            board.updateBoard(9,5,8,4,Color.BLACK);
//            board.updateBoard(8,4,5,7,Color.BLACK);
            }
        }
        Platform.runLater(() -> root.setCenter(board));
    }

    public void displayAnnouncement(String msg) {
        Platform.runLater(() -> label.setText(msg));
    }

    public void displayMove(int oldX, int oldY, int newX, int newY, Color color) {
        Platform.runLater(() -> board.updateBoard(oldX, oldY, newX, newY, color));
    }

    public static void main(String[] args) {
        launch(args);
    }


}