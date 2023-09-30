package dictionary;

import com.almasb.fxgl.multiplayer.PropertyRemoveReplicationEvent;
import module.SQLite.SQLiteConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Dictionary;
import java.util.Scanner;

public class DictionaryManagement extends SQLiteConnection {

    public SQLiteConnection sqLiteConnection2 = new SQLiteConnection();
    public void dictionarySearcher() {

    }

    public void dictionarySpelling() {

    }

    public void deleteWord(String word) {
        String deleteQuery = "DELETE FROM" + super.dataTable + " WHERE word LIKE " + word;

        try {
            PreparedStatement preparedStatement;
            preparedStatement = sqLiteConnection2.connection.prepareStatement(deleteQuery);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void insertWord() {

    }

    public void editWord() {

    }

    public static void main(String[] args) {
        DictionaryManagement dictionaryManagement = new DictionaryManagement();
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        //dictionaryManagement.
    }

}
