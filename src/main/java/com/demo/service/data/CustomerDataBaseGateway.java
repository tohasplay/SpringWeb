package com.demo.service.data;

import com.demo.businesscore.Customer;
import com.demo.businesscore.Order;
import com.demo.exception.NotFoundException;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class CustomerDataBaseGateway implements CustomerDataBaseAccess{


    @Override
    public ArrayList<Customer> getAllData() {
        ResultSet set = DataBaseConnector.executeQuery("SELECT * FROM customer");
        assert set != null;
        ArrayList<Customer> result = new ArrayList<>();
        while (true) {
            try {
                if (set.next()) {
                    result.add(new Customer(
                            set.getLong("id"),
                            set.getString("name"),
                            set.getString("mail"),
                            set.getString("phone"),
                            set.getString("password")
                    ));
                } else break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DataBaseConnector.closeConnections();
        return result;
    }

    @Override
    public Customer findById(long id) {
        ResultSet set = DataBaseConnector.executeQuery("SELECT * FROM customer WHERE id='" + id + "'");
        assert set != null;
        try {
            if (set.next())
                return new Customer(
                        set.getLong("id"),
                        set.getString("name"),
                        set.getString("mail"),
                        set.getString("phone"),
                        set.getString("password")
                );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBaseConnector.closeConnections();
        }
        throw new NotFoundException();
    }

    @Override
    public Customer putObject(Customer data) {
        DataBaseConnector.executeUpdateQuery(
                "INSERT INTO customer VALUES(0, '" +
                        data.getName() + "', '" +
                        data.getMail().toLowerCase() + "', '" +
                        data.getPhone() + "', '" +
                        data.getPassword() + "')"
        );
        ResultSet set = DataBaseConnector.executeQuery("SELECT max(id) FROM customer");
        try {
            assert set != null;
            if (set.next())
                data.setId(set.getLong(1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DataBaseConnector.closeConnections();
        }
        return data;
    }

    @Override
    public void deleteObject(Customer data) {
        DataBaseConnector.executeUpdateQuery("DELETE FROM customer WHERE mail='" + data.getMail().toLowerCase() + "'");
    }

    @Override
    public void updateObject(long id, Customer data) {
        DataBaseConnector.executeUpdateQuery("UPDATE customer SET " +
                "name='" + data.getName() + "'," +
                "mail='" + data.getMail() + "'," +
                "phone='" + data.getPhone() + "'," +
                "password='" + data.getPassword() + "' "
                + "WHERE id='" + id + "'"
        );
    }

    @Override
    public boolean contains(Customer data) {
        ResultSet set =
                DataBaseConnector.executeQuery("SELECT * FROM customer WHERE mail='" + data.getMail().toLowerCase() + "'");
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
    public ArrayList<Order> getOrders(long id) {
        return null;
    }
}
