package com.demo.service.data;

import org.springframework.beans.factory.annotation.Value;

import java.sql.*;

public class DataBaseConnector {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet set;

    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/demo";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";


    /**
     * Executing query without data manipulation
     * All uses must close connections after execution
     * @see DataBaseConnector#closeConnections()
     * @param query sql query to execute
     * @return database response as ResultSet
     * @see ResultSet
     */
    public static ResultSet executeQuery(String query) {
        try {
            getConnection();
            set = statement.executeQuery(query);

            return set;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Executing query containing data manipulation, do not require closing connections
     * @param query sql query e.g. update/insert/delete
     */
    public static void executeUpdateQuery(String query){
        try {
            getConnection();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnections();
        }
    }

    private static void getConnection() throws SQLException {
        connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
        statement = connection.createStatement();
    }

    public static void closeConnections(){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

}
