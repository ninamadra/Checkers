package org.example.Client;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.util.Objects;

public class boardController {
    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button b4;
    @FXML
    private Button b5;
    @FXML
    private Button b6;
    @FXML
    private Button b7;
    @FXML
    private Button b8;
    @FXML
    private Button b9;
    private boolean isFirstPlayer = true;

    public void buttonClickHandler(final ActionEvent evt) {

        Button clickedButton = (Button) evt.getTarget();
        String buttonLabel = clickedButton.getText();

        if ("".equals(buttonLabel) && isFirstPlayer) {
            clickedButton.setText("X");
            isFirstPlayer = false;
        } else if ("".equals(buttonLabel)) {
            clickedButton.setText("O");
            isFirstPlayer = true;
        }

    }
}
