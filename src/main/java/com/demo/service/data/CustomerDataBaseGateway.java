package com.demo.service.data;

import com.demo.dto.Customer;
import com.demo.dto.Order;
import com.demo.exception.NotFoundException;
import com.demo.service.data.DBConnection.AutocloseableResultSet;
import com.demo.service.data.DBConnection.DataBaseConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//DONE: jdbc right way + try with resources
//TODO: rewrite spring data (repository)
@Component
public class CustomerDataBaseGateway implements CustomerDataBaseAccess {

    private final DataBaseConnector dataBaseConnector;

    @Autowired
    public CustomerDataBaseGateway(DataBaseConnector dataBaseConnector) {
        this.dataBaseConnector = dataBaseConnector;
    }

    @Override
    public ArrayList<Customer> getAll() {
        ArrayList<Customer> result = new ArrayList<>();
        try (AutocloseableResultSet set = dataBaseConnector.executeQuery("SELECT * FROM customer")) {
            assert set != null;
            while (true) {
                if (set.getResultSet().next()) {
                    result.add(new Customer(
                            set.getResultSet().getLong("id"),
                            set.getResultSet().getString("name"),
                            set.getResultSet().getString("mail"),
                            set.getResultSet().getString("phone"),
                            set.getResultSet().getString("password")
                    ));
                } else break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Customer findById(long id) {
        try (AutocloseableResultSet set = dataBaseConnector.executeQuery("SELECT * FROM customer WHERE id='" + id + "'")) {
            assert set != null;
            if (set.getResultSet().next())
                return new Customer(
                        set.getResultSet().getLong("id"),
                        set.getResultSet().getString("name"),
                        set.getResultSet().getString("mail"),
                        set.getResultSet().getString("phone"),
                        set.getResultSet().getString("password")
                );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new NotFoundException();
    }

    @Override
    public Customer put(Customer data) {
        dataBaseConnector.executeUpdate(
                "INSERT INTO customer VALUES(0, '" +
                        data.getName() + "', '" +
                        data.getMail().toLowerCase() + "', '" +
                        data.getPhone() + "', '" +
                        data.getPassword() + "')"
        );

        try (AutocloseableResultSet set = dataBaseConnector.executeQuery("SELECT max(id) FROM customer")) {
            assert set != null;
            if (set.getResultSet().next())
                data.setId(set.getResultSet().getLong(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public void delete(long id) {
        dataBaseConnector.executeUpdate("DELETE FROM customer WHERE id=" + id);
    }

    @Override
    public void update(long id, Customer data) {
        dataBaseConnector.executeUpdate("UPDATE customer SET " +
                "name='" + data.getName() + "'," +
                "mail='" + data.getMail() + "'," +
                "phone='" + data.getPhone() + "'," +
                "password='" + data.getPassword() + "' "
                + "WHERE id='" + id + "'"
        );
    }

    @Override
    public boolean contains(Customer data) {
        try (AutocloseableResultSet set =
                     dataBaseConnector
                             .executeQuery(
                                     "SELECT * FROM customer WHERE mail='" + data.getMail().toLowerCase() + "'"
                             )
        ) {
            if (set.getResultSet().next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<Order> getOrders(long id) {
        ArrayList<Order> orders = new ArrayList<>();
        try(ResultSet set = dataBaseConnector.executeQuery(
                "SELECT * FROM `order` WHERE customer_id=" + id
        ).getResultSet()) {
            while (set.next()){
                orders.add(
                        new Order(
                                set.getLong("id"),
                                set.getString("text"),
                                set.getFloat("price")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
