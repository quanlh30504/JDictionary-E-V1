package dictionary;

import com.almasb.fxgl.multiplayer.PropertyRemoveReplicationEvent;
import module.SQLite.SQLiteConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Dictionary;
import java.util.Scanner;

public class DictionaryManagement extends SQLiteConnection {

    public SQLiteConnection sqLiteConnection2 = new SQLiteConnection();
    public String dictionarySearcher(String word) throws SQLException {
        String searchQuery = "SELECT * FROM" + dataTable + " WHERE word LIKE " + word;
        ResultSet resultSet = sqLiteConnection2.query(searchQuery);

        return resultSet.getString("html");
    }

    public void dictionarySpelling() {

    }

    public void deleteWord(String word) {
        String deleteQuery = "DELETE FROM" + dataTable + " WHERE word LIKE " + word;

        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void insertWord() {


    }

    public void editWord(String word, String wordExplain) {
        String editQuery = "UPDATE " + dataTable + " SET html = ?" + " WHERE word = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(editQuery);
            preparedStatement.setString(1, word);
            preparedStatement.setString(2, wordExplain);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DictionaryManagement dictionaryManagement = new DictionaryManagement();
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        //dictionaryManagement.
    }

}
