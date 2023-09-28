package module.SQLite;

import java.sql.*;

public class SQLiteConnection {

    public static SQLiteConnection sqLiteConnection;
    public Connection connection = null;

    public SQLiteConnection() {

    }

    public SQLiteConnection getSqLiteConnection() {
        if (sqLiteConnection != null) {
            return sqLiteConnection = new SQLiteConnection();
        }
        return sqLiteConnection;
    }

    public void setConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:dict_hh.db");
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

    public static void main(String[] args) {
        SQLiteConnection sqLiteConnection1 = new SQLiteConnection();
        sqLiteConnection1.setConnection();
    }

}
