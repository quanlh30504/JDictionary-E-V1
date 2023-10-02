package dictionary;

import com.almasb.fxgl.multiplayer.PropertyRemoveReplicationEvent;
import module.SQLite.SQLiteConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Dictionary;
import java.util.Scanner;
import utils.Utils;
import module.TextToSpeech;

import javax.speech.AudioException;
import javax.speech.EngineException;

public class DictionaryManagement extends SQLiteConnection {

    //public static SQLiteConnection sqLiteConnection2 = new SQLiteConnection();
    public String dictionarySearcher(String word, SQLiteConnection sqLiteConnection) throws SQLException {

        String searchQuery = "SELECT * FROM " + dataTable + " WHERE word LIKE " + "'" + word + "'";
        System.out.println(searchQuery);
        ResultSet resultSet = sqLiteConnection.query(searchQuery);

        if (resultSet != null) {
            return resultSet.getString(3);
        } else {
            return "Đần";
        }

    }

    public void dictionarySpelling(String word) {
        TextToSpeech textToSpeech = new TextToSpeech();
        try {
            textToSpeech.Spelling(word);
        } catch (EngineException e) {
            throw new RuntimeException(e);
        } catch (AudioException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteWord(String word) {
        String deleteQuery = "DELETE FROM" + dataTable + " WHERE word LIKE " + "'" + word + "'";

        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void insertWord(Word word) {

        String insertQuery = "INSERT INTO " + dataTable + "(id, word, html, favorite)" + "VALUES(?,?,?, 0)";
        String getMaxID = "SELECT id FROM " + dataTable + " WHERE id = (SELECT MAX(id) FROM " + dataTable + ")";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getMaxID);
            int numOfRows = preparedStatement.getMaxRows();
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, numOfRows + 1);
            preparedStatement.setString(2, Word.wordFound);
            preparedStatement.setString(3, Word.wordExplaination);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void editWord(String word, String wordExplain) {
        String editQuery = "UPDATE " + dataTable + " SET html = ?" + " WHERE word = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(editQuery);
            preparedStatement.setString(1, wordExplain);
            preparedStatement.setString(2, word);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        DictionaryManagement dictionaryManagement = new DictionaryManagement();
        SQLiteConnection sqLiteConnection3 = new SQLiteConnection();
        sqLiteConnection3.setConnection(dbName);
        Utils utils = new Utils();
        Scanner scanner = new Scanner(System.in);
        String data = scanner.nextLine();

        System.out.println(dictionaryManagement.dictionarySearcher(data, sqLiteConnection3));
        dictionaryManagement.dictionarySpelling(data);
    }

}
