package com.demo.service.data.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

//DONE: connection pool + cashed connections
public class DataBaseConnector{

    private final int CAPACITY = 20;

    private final BlockingQueue<Connection> connections = new ArrayBlockingQueue<>(CAPACITY);

    public DataBaseConnector() throws SQLException {
        for (int i = 0; i < CAPACITY; i++) {
            String CONNECTION_URL = "jdbc:mysql://localhost:3306/demo";
            String USERNAME = "root";
            String PASSWORD = "Anton050";
            connections.add(new CashedConnection(DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD)));
        }
    }

    public Connection getConnection() {
        try{
            return connections.poll(2, TimeUnit.SECONDS);
        }catch (InterruptedException e){
            return null;
        }
    }


    public void putConnection(Connection connection){
        connections.add(connection);
    }

    /**
     * must use with try with resource
     * @param sql query without mutation
     * @return {@link AutocloseableResultSet}
     */
    public AutocloseableResultSet executeQuery(String sql){
        AutocloseableResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            resultSet = new AutocloseableResultSet(
                    connection.prepareStatement(sql).executeQuery()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        putConnection(connection);
        return resultSet;
    }

    public void executeUpdate(String sql){
        Connection connection = getConnection();
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        putConnection(connection);
    }

}
