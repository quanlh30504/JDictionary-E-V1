package com.example.jdictionaryev1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Chào mừng bạn đã đến với bình nguyên vô tận");
    }
}