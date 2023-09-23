package com.example.jdictionaryev1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setTitle("JDictionary English-Vietnamese");
        stage.setScene(scene);
        stage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/5027/5027435.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}