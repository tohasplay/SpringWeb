package com.demo.service.data.JDBC;

import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AutocloseableResultSet implements AutoCloseable{

    @Getter
    private final ResultSet resultSet;

    public AutocloseableResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    @Override
    public void close() throws SQLException {
        resultSet.close();
    }
}
