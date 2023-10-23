package com.example.jdictionaryev1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import module.SQLite.SQLiteConnection;

import java.io.IOException;
import java.sql.*;
public class AddQuestionController extends SQLiteConnection {


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
