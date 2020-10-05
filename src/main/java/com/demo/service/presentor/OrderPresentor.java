package com.demo.service.presentor;

import com.demo.businesscore.Order;
import com.demo.interacor.CustomerManager;
import com.demo.service.data.OrderDataBaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OrderPresentor implements OrderUndefinedPresentor {

    private final OrderDataBaseAccess orderDataBaseAccess;
    private final CustomerManager customerManager;

    @Autowired
    private OrderPresentor(OrderDataBaseAccess orderDataBaseAccess,
                           CustomerManager customerManager) {
        this.orderDataBaseAccess = orderDataBaseAccess;
        this.customerManager = customerManager;
    }

    @Override
    public Order add(Order data, long id, String password) {
        return customerManager.makeOrder(id, data.getText(), password);
    }

    @Override
    public Order get(long id) {
        return orderDataBaseAccess.getById(id);
    }

    @Override
    public void delete(long id) {
        orderDataBaseAccess.deleteObject(id);
    }

    @Override
    public Order update(Order data) {
        orderDataBaseAccess.updateObject(data);
        return data;
    }


    @Override
    public ArrayList<Order> getAll() {
        return orderDataBaseAccess.getAllOrders();
    }
}
