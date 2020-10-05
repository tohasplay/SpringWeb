package com.demo.service.data;

import com.demo.businesscore.Order;

import java.util.ArrayList;

public interface OrderDataBaseAccess{

    ArrayList<Order> getAllOrders();
    void putObject(Order data, long id);
    void deleteObject(long id);
    void updateObject(Order data);
    Order getById(long id);
    boolean verifyUser(long id, String password);
    Order getLastOrder();

    /**
     * uses for calculation total for particular customer
     * @param id customer id
     * @return list of all orders made by customer
     */
    ArrayList<Order> getOrdersByCustomer(long id);
}
