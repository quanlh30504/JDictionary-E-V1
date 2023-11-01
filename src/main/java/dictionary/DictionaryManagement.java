package dictionary;

import com.almasb.fxgl.multiplayer.PropertyRemoveReplicationEvent;
import module.SQLite.SQLiteConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Scanner;

import searchingAlgorithm.Trie;
import utils.Utils;
import module.TextToSpeech;

import javax.speech.AudioException;
import javax.speech.EngineException;

public class DictionaryManagement extends SQLiteConnection {

    //public static SQLiteConnection sqLiteConnection2 = new SQLiteConnection();
    public Trie trie = new Trie();
    public List<String> wordList = new ArrayList<>();

    public DictionaryManagement() {

    }

    public String dictionarySearcher(String word, SQLiteConnection sqLiteConnection) throws SQLException {

        String searchQuery = "SELECT * FROM " + dataTable + " WHERE word LIKE " + "'" + word + "'";
        //System.out.println(searchQuery);
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

    /*TODO:- Work more on adjusting id after delete since id is not auto_increment (not that important?).
           - Check if the word is in the database or not.
           - Standardize String word.
         */
    public void deleteWord(String word) {
        SQLiteConnection sqLiteConnection1 = new SQLiteConnection();
        sqLiteConnection1.setConnection(dbName);
        String deleteQuery = "DELETE FROM " + dataTable + " WHERE word = " + '"' + word +'"';
        System.out.println(deleteQuery);
        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void insertWord(Word word) throws SQLException {
        SQLiteConnection sqLiteConnection1 = new SQLiteConnection();
        sqLiteConnection1.setConnection(dbName);
        String insertQuery = "INSERT INTO " + dataTable + "(id, word, html, pronounce)" + "VALUES(?,?,?, 0)";
        String getMaxID = "SELECT id FROM " + dataTable + " WHERE id = (SELECT MAX(id) FROM " + dataTable + ")";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getMaxID);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getMaxID);
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, resultSet.getInt("id")+ 1);
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

    public void getAllWord(String dbName) {
        String query = "SELECT WORD FROM AV";
        int count = 0;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                String word = resultSet.getString("WORD");
                //System.out.println(word);
                wordList.add(word);
                count++;
            }
            //System.out.print(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Trie getTrie() {
        return trie;
    }

    private void setTrie() {
        try {
            for (String word : wordList) {
                trie.insert(word);
            }
        } catch (NullPointerException e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    public static void main(String[] args) throws SQLException {
        DictionaryManagement dictionaryManagement = new DictionaryManagement();
        SQLiteConnection sqLiteConnection3 = new SQLiteConnection();
        sqLiteConnection3.setConnection(dbName);
        Utils utils = new Utils();
        //Scanner scanner = new Scanner(System.in);
        //String data = scanner.nextLine();
        dictionaryManagement.getAllWord(dbName);
        dictionaryManagement.setTrie();
        Trie trie1 = dictionaryManagement.getTrie();
        List<String> ans = trie1.autoComplete("a");
        for (String a : ans) {
            System.out.println(a);
        }
        /*
        Scanner scanner = new Scanner(System.in);
        String data = scanner.nextLine();

        //System.out.println(dictionaryManagement.dictionarySearcher(data, sqLiteConnection3));
        //dictionaryManagement.dictionarySpelling(data);
        //data = dictionaryManagement.dictionarySearcher(data, sqLiteConnection3);
        //System.out.println(data);
        //System.out.println(utils.getTextFromHTML(data));
        dictionaryManagement.dictionarySpelling(data);
        data = dictionaryManagement.dictionarySearcher(data, sqLiteConnection3);
        System.out.println(data);
         */
        /* System.out.println(utils.getTextFromHTML(data)); */
        Word news = new  Word("Balon d'or","Bóng vàng");
        dictionaryManagement.deleteWord("Balon d'or");
    }
}
