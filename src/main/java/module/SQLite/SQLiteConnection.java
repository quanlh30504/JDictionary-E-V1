package module.SQLite;


import java.sql.*;

public class SQLiteConnection {

    public static SQLiteConnection sqLiteConnection = new SQLiteConnection();
    public static Connection connection = null;

    public static String dbName = "jdbc:sqlite:src/main/resources/database/dict_hh.db";

    public static String dataTable = "AV";

    public static String question = "QUESTION";

//    public SQLiteConnection() {
//        sqLiteConnection = new SQLiteConnection();
//    }

    public SQLiteConnection getSqLiteConnection() {
        if (sqLiteConnection != null) {
            return sqLiteConnection = new SQLiteConnection();
        }
        return null;
    }

    public void setConnection(String path) {
        try {
            connection = DriverManager.getConnection(path);
            if (connection != null) {
                System.out.println("Connected");
            } else {
                System.out.println("Can't connect to SQLite");
            }
        } catch (SQLException e) {
            System.out.println("Can't connect to SQLite");
            e.printStackTrace();
        }
    }

    // Query
    public ResultSet query(String searchQuery) {

        ResultSet resultSet = null;

        try {
            PreparedStatement preparedStatement;
            if (connection != null) {
                preparedStatement = connection.prepareStatement(searchQuery);
                resultSet = preparedStatement.executeQuery();
            }

//            if (resultSet != null) {
//                while (resultSet.next() == true) {
//                    String id = resultSet.getString(3);
//                    System.out.println(id);
//                }
//            }

            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public static void main(String[] args) throws SQLException {
        SQLiteConnection sqLiteConnection1 = new SQLiteConnection();
        sqLiteConnection1.setConnection(dbName);
        String searchQuery = "SELECT * FROM " + dataTable + " WHERE word LIKE " + "'neural'";
        System.out.println(sqLiteConnection1.query(searchQuery));
        String a = "A";
        try (Statement statement = connection.createStatement()) {
            String insertQuery = "INSERT INTO " + question + " (DESCRIPTION,ANSWER) VALUES ('What is your name?','Your mom')";
            statement.executeUpdate(insertQuery);
        } catch (SQLException E) {
            E.printStackTrace();
        }
    }

}
