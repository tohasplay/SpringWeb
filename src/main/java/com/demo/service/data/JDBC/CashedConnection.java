package com.demo.service.data.JDBC;
import com.mysql.cj.jdbc.ConnectionImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

public class CashedConnection extends ConnectionImpl {

    private final Connection connection;

    protected CashedConnection(Connection connection){
        this.connection = connection;
    }

    private final ConcurrentHashMap<String, PreparedStatement> statements = new ConcurrentHashMap<>();

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        PreparedStatement statement = statements.get(sql);
        if (statement == null){
            statement = connection.prepareStatement(sql);
            statements.put(sql, statement);
        }
        return statement;
    }
}
