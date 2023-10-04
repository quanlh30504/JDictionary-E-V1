package com.example.jdictionaryev1;

import graphic.DictionaryScene;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage stg;
    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
        Parent root = FXMLLoader.load(getClass().getResource("LoginGUI.fxml"));
        stg.setTitle("JDictionary English-Vietnamese");
        stg.getIcons().add(new Image("dictionary_icon.png"));
        stg.setScene(new Scene(root,780  ,460));
        stg.show();

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

    //change screen
    public void changeScreen(String fxml)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        stg.setScene(new Scene(root,840 ,540));
        stg.show();

//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root,840,540);
//        stage.setScene(scene);
//        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}