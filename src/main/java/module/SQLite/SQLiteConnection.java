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
            this.connection = DriverManager.getConnection(path);
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
    public void query() {

        try {
            PreparedStatement preparedStatement;
            preparedStatement = this.connection.prepareStatement("SELECT * FROM " + dataTable);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next() == true) {
                int id = resultSet.getInt(1);
                System.out.println(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SQLiteConnection sqLiteConnection1 = new SQLiteConnection();
        sqLiteConnection1.setConnection(dbName);
        sqLiteConnection1.query();
    }

}
