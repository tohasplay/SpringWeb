package com.demo.service.data;

import com.demo.businesscore.Order;
import com.demo.exception.NotFoundException;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class OrderDataBaseGateway implements OrderDataBaseAccess {


    @Override
    public ArrayList<Order> getAllOrders() {
        ResultSet set = DataBaseConnector.executeQuery("SELECT * FROM `order`");
        ArrayList<Order> orders = new ArrayList<>();
        assert set != null;
        getOrdersAsList(set, orders);
        DataBaseConnector.closeConnections();
        return orders;
    }

    @Override
    public void putObject(Order data, long id) {
        DataBaseConnector.executeUpdateQuery(
                "INSERT INTO `order` VALUES(0, '" + data.getText() + "'," +
                        "'" + data.getPrice() + "'," +
                        "'" + id + "')"
        );

    }

    @Override
    public void deleteObject(long id) {
        DataBaseConnector.executeUpdateQuery(
                "DELETE FROM `order` WHERE id=" + id
        );
    }

    @Override
    public void updateObject(Order data) {
        DataBaseConnector.executeUpdateQuery("UPDATE `order` SET " +
                "text='" + data.getText() + "'," +
                "price=" + data.getPrice() +
                " WHERE id=" + data.getId());
    }

    @Override
    public Order getById(long id) {
        ResultSet set = DataBaseConnector.executeQuery("SELECT * FROM `order` WHERE id=" + id);
        assert set != null;
        return getOrder(set);
    }

    @Override
    public boolean verifyUser(long id, String password) {
        ResultSet set = DataBaseConnector.executeQuery(
                "SELECT * FROM customer WHERE id=" + id
                        + " AND password='" + password + "'"
        );
        assert set != null;
        try {
            if (set.next()) {
                DataBaseConnector.closeConnections();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Order getLastOrder() {
        ResultSet set = DataBaseConnector.executeQuery("SELECT * FROM `order` GROUP BY id HAVING id = max(id)");
        assert set != null;
        return getOrder(set);
    }

    @Override
    public ArrayList<Order> getOrdersByCustomer(long id) {
        ResultSet set = DataBaseConnector.executeQuery("SELECT * FROM `order` WHERE customer_id=" + id);
        assert set != null;
        ArrayList<Order> orders = new ArrayList<>();
        getOrdersAsList(set, orders);
        DataBaseConnector.closeConnections();
        return orders;
    }

    private Order getOrder(ResultSet set) {
        try {
            if (set.next())
                return new Order(
                        set.getLong("id"),
                        set.getString("text"),
                        set.getFloat("price"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBaseConnector.closeConnections();
        }
        throw new NotFoundException();
    }

    private void getOrdersAsList(ResultSet set, ArrayList<Order> orders) {
        while (true) {
            try {
                if (!set.next()) break;
                orders.add(
                        new Order(
                                set.getLong("id"),
                                set.getString("text"),
                                set.getFloat("price")
                        )
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

