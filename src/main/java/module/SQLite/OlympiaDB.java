package module.SQLite;

import java.sql.*;

public class OlympiaDB extends SQLiteConnection {
    public static SQLiteConnection sqLiteConnection1 = new SQLiteConnection();

    public void setConnectionOlympia() {
        sqLiteConnection1.setConnection("jdbc:sqlite:src/main/resources/database/Olympia.db");
    }

    public ResultSet executeStartUp() throws SQLException {
        String dataTable = "KD";
        String searchQuery = "SELECT Question FROM " + dataTable;
        PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
        return preparedStatement.executeQuery();
    }

    public ResultSet executeObstacle(String question) throws SQLException {
        String dataTable = "VCNV";
        String searchQuery = "SELECT " + question +  " FROM " + dataTable;
        PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
        return preparedStatement.executeQuery();
    }

    public ResultSet addImageR2() throws SQLException {
        String dataTable = "VCNV";
        String searchQuery = "SELECT Image FROM " + dataTable;
        PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
        return preparedStatement.executeQuery();
    }

    public ResultSet executeSpeedUp() throws SQLException {
        String dataTable = "TT";
        String searchQuery = "SELECT Question FROM " + dataTable;
        PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
        return preparedStatement.executeQuery();
    }

    public ResultSet executeFinish() throws SQLException {
        String dataTable = "VD";
        String searchQuery = "SELECT Question FROM " + dataTable;
        PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
        return preparedStatement.executeQuery();
    }

    public static void main(String[] args) throws SQLException {
//        try {
//            sqLiteConnection1.setConnection(dbName1);
////            connection = (Connection) sqLiteConnection1.getSqLiteConnection();
//            String dataTable = "KD";
//            String searchQuery = "SELECT Question FROM " + dataTable;
//            PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            System.out.println(resultSet);
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString(1));
//                //System.out.println(1);
//            }
//            resultSet.close();
//            statement.close();
//            connection.close();
//        } catch (SQLException E) {
//            E.printStackTrace();
//        }
    }

}
