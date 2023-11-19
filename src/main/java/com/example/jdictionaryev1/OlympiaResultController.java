package com.example.jdictionaryev1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class OlympiaResultController {
    @FXML
    public Label score;
    public void initialize(){
        int Score = OlympiaController.Mark;
        score.setText(String.valueOf(Score));

    }
    @FXML
    public void returnMenuGame(ActionEvent event) throws IOException {
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.changeScreen("OlympiaStart.fxml", 1080, 608);
    }
}
