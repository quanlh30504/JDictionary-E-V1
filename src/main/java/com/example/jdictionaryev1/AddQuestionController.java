package com.example.jdictionaryev1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import module.SQLite.SQLiteConnection;

import java.io.IOException;
import java.sql.*;
public class AddQuestionController extends SQLiteConnection {

    @FXML
    public Stage stage;
    @FXML
    public Scene scene;
    public Parent root;
    @FXML
    public void switchToStartOlympiaGame(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("OlympiaStart.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1080, 608);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private Button outPage;
    @FXML
    private TextField answerInsert;

    @FXML
    private TextField questionInsert;
    public  void addQuestion(ActionEvent event) throws SQLException {
        SQLiteConnection sqLiteConnection1 = new SQLiteConnection();
        sqLiteConnection1.setConnection(dbName);
        String question = questionInsert.getText();
        String answer = answerInsert.getText();
        try (Statement statement = connection.createStatement()) {
            String insertQuery = "INSERT INTO " + SQLiteConnection.question + " (DESCRIPTION,ANSWER) VALUES " +
                    "('"+question+"','"+answer+"')";

            statement.executeUpdate(insertQuery);
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Add question successfully");
            alert.show();
            questionInsert.clear();
            answerInsert.clear();
        } catch (SQLException E) {
            E.printStackTrace();
        }
    }
    public void QuitPage(ActionEvent event) throws IOException {
        //chuyển về màn hình app
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.changeScreen("Dictionary.fxml",840,540);
    }
}
