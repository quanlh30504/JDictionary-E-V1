package com.example.jdictionaryev1;

import graphic.DictionaryScene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dictionary.fxml"));
        stage.setTitle("JDictionary English-Vietnamese");
        stage.getIcons().add(new Image("dictionary_icon.png"));
        stage.setScene(new Scene(root,840,540));
        stage.show();

        // Dictionary Scence
        DictionaryScene dictionaryScence = new DictionaryScene();

        // Search bar on click
        searchBar();

        // tạo mục hỏi logout cho nút X trên window
        stage.setOnCloseRequest(event -> {
            event.consume();
            dictionaryScence.logout(stage);
        });
    }

    /* SearchBar */
    public void searchBar() {
        TextField englishSearchBar = new TextField();
        englishSearchBar.setId("textFieldSearch");
        //englishSearchBar.setPromptText("English Search");
        englishSearchBar.setOnMouseClicked(event -> englishSearchBar.clear());
        System.out.println(englishSearchBar.getId());
    }

    public static void main(String[] args) {
        launch();
    }
}