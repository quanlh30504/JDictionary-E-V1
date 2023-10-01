package module.SQLite;

import java.sql.*;

public class SQLiteConnection {

    public static SQLiteConnection sqLiteConnection;
    public static Connection connection = null;

    public static String dbName = "jdbc:sqlite:src/main/resources/database/dict_hh.db";

    public static String dataTable = "AV";

    public SQLiteConnection() {
        sqLiteConnection = new SQLiteConnection();
    }

    public SQLiteConnection getSqLiteConnection() {
        if (sqLiteConnection != null) {
            return sqLiteConnection = new SQLiteConnection();
        }
        return sqLiteConnection;
    }

    public void setConnection(String path) {
        try {
            connection = DriverManager.getConnection(path);
            if (this.connection != null) {
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
            preparedStatement = this.connection.prepareStatement(searchQuery);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next() == true) {
                int id = resultSet.getInt(1);
                System.out.println(id);
            }

            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public static void main(String[] args) {
        SQLiteConnection sqLiteConnection1 = new SQLiteConnection();
        sqLiteConnection1.setConnection(dbName);
        String searchQuery = "SELECT * FROM " + dataTable;
        System.out.println(sqLiteConnection1.query(searchQuery));
    }

}
