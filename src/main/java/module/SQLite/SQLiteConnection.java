package module.SQLite;

import java.sql.*;

public class SQLiteConnection {

    public static SQLiteConnection sqLiteConnection;
    public static Connection connection = null;

    public SQLiteConnection() {

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
    public void query() {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM AV");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next() == true) {
                int id = resultSet.getInt(1);
                System.out.println(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        SQLiteConnection sqLiteConnection1 = new SQLiteConnection();
        sqLiteConnection1.setConnection("jdbc:sqlite:src/main/resources/database/dict_hh.db");
        sqLiteConnection1.query();
    }

}
