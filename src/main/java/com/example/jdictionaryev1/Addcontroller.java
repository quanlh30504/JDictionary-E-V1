package com.example.jdictionaryev1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import module.SQLite.SQLiteConnection;

import java.sql.*;
public class Addcontroller extends SQLiteConnection {



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
}
