package com.demo.service.data;

import com.demo.dto.Order;
import com.demo.exception.NotFoundException;
import com.demo.service.data.DBConnection.AutocloseableResultSet;
import com.demo.service.data.DBConnection.DataBaseConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class OrderDataBaseGateway implements OrderDataBaseAccess {

    private final DataBaseConnector dataBaseConnector;

    @Autowired
    public OrderDataBaseGateway(DataBaseConnector dataBaseConnector) {
        this.dataBaseConnector = dataBaseConnector;
    }

    @Override
    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        try (AutocloseableResultSet set = dataBaseConnector.executeQuery("SELECT * FROM `order`")) {
            assert set != null;
            getOrdersAsList(set, orders);
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public void put(Order data, long id) {
        dataBaseConnector.executeUpdate(
                "INSERT INTO `order` VALUES(0, '" + data.getText() + "'," +
                        "'" + data.getPrice() + "'," +
                        "'" + id + "')"
        );

    }

    @Override
    public void delete(long id) {
        dataBaseConnector.executeUpdate(
                "DELETE FROM `order` WHERE id=" + id
        );
    }

    @Override
    public void update(Order data) {
        dataBaseConnector.executeUpdate("UPDATE `order` SET " +
                "text='" + data.getText() + "'," +
                "price=" + data.getPrice() +
                " WHERE id=" + data.getId());
    }

    @Override
    public Order getById(long id) {
        try (AutocloseableResultSet set = dataBaseConnector.executeQuery("SELECT * FROM `order` WHERE id=" + id)) {
            assert set != null;
            return getOrder(set);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean verifyUser(long id, String password) {
        try (
                AutocloseableResultSet set = dataBaseConnector.executeQuery(
                        "SELECT * FROM customer WHERE id=" + id
                                + " AND password='" + password + "'"
                )
        ) {
            assert set != null;
            if (set.getResultSet().next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Order getLastOrder() {
        try (
                AutocloseableResultSet set
                        = dataBaseConnector.executeQuery("SELECT * FROM `order` GROUP BY id HAVING id = max(id)")
        ) {
            assert set != null;
            return getOrder(set);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Order getOrder(AutocloseableResultSet set) {
        try {
            if (set.getResultSet().next())
                return new Order(
                        set.getResultSet().getLong("id"),
                        set.getResultSet().getString("text"),
                        set.getResultSet().getFloat("price"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new NotFoundException();
    }

    private void getOrdersAsList(AutocloseableResultSet set, ArrayList<Order> orders) {
        while (true) {
            try {
                if (!set.getResultSet().next()) break;
                orders.add(
                        new Order(
                                set.getResultSet().getLong("id"),
                                set.getResultSet().getString("text"),
                                set.getResultSet().getFloat("price")
                        )
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

