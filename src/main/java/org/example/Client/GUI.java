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

/**
 * Class for graphical user interface
 */
public class GUI extends Application
{
    AbstractGUIBoard board;
    GameController gameController;
    BorderPane root;
    MenuItem classic = new MenuItem("classic");
    MenuItem thai = new MenuItem("thai");
    MenuItem polish = new MenuItem("polish");
    MenuItem bot = new MenuItem("Odtworz partie");
    Label label;

    /**
     * Starts thread with gui view
     * @param primaryStage
     */
    @Override
    public void start(final Stage primaryStage) {
        gameController = new GameController(this);
        root = new BorderPane();
        label = new Label("" + gameController.getColor());

        classic.setOnAction(e -> gameController.startGame("CLASSIC"));
        thai.setOnAction(e -> gameController.startGame("THAI"));
        polish.setOnAction(e -> { gameController.startGame("POLISH"); });
        bot.setOnAction(e -> { gameController.startGame("BOT"); });
        HBox top = new HBox();
        Menu type = new Menu("type");
        Menu options = new Menu("options");
        type.getItems().addAll(classic, thai, polish);
        options.getItems().add(bot);
        MenuBar menuBar = new MenuBar(type, options);
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

    /**
     * Method sets proper type of game and updates view
     * @param var type of game
     */
    public void setType(String var) {
        switch(var) {
            case "THAI" -> board = new ThaiGUIBoard(gameController);
            case "CLASSIC" -> board = new ClassicGUIBoard(gameController);
            case "POLISH" -> board = new PolishGUIBoard(gameController);
        }
        Platform.runLater(() -> root.setCenter(board));
    }

    /**
     * Method displays message to user in GUI
     * @param msg
     */
    public void displayAnnouncement(String msg) {
        Platform.runLater(() -> label.setText(msg));
    }

    /** Method updates view and position after accepted move
     * @param oldX
     * @param oldY
     * @param newX
     * @param newY
     * @param color
     */
    public void displayMove(int oldX, int oldY, int newX, int newY, Color color) {
        Platform.runLater(() -> board.updateBoard(oldX, oldY, newX, newY, color));
    }

    public void disable() {
        Platform.runLater(() -> root.setCenter(null));
    }

    public static void main(String[] args) {
        launch(args);
    }


}