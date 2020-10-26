package com.demo.service.presentor;

import com.demo.dto.Order;
import com.demo.dto.builder.AutoPricedOrder;
import com.demo.dto.builder.OrderBuilder;
import com.demo.dto.builder.OrderDirector;
import com.demo.exception.NotFoundException;
import com.demo.service.data.OrderDataBaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OrderPresentorImpl implements OrderPresentor {

    private final OrderDataBaseAccess orderDataBaseAccess;

    @Autowired
    private OrderPresentorImpl(OrderDataBaseAccess orderDataBaseAccess) {
        this.orderDataBaseAccess = orderDataBaseAccess;
    }

    @Override
    public Order add(Order data, long id, String password) {
        if (!orderDataBaseAccess.verifyUser(id, password))
            throw new NotFoundException();
        OrderDirector orderDirector = new OrderDirector();
        orderDirector.setOrderBuilder(new AutoPricedOrder(data.getText()));
        orderDirector.constructOrder();
        return orderDataBaseAccess.put(orderDirector.getOrder(), id);
    }

    @Override
    public Order get(long id) {
        return orderDataBaseAccess.getById(id);
    }

    @Override
    public void delete(long id) {
        orderDataBaseAccess.delete(id);
    }

    @Override
    public Order update(Order data) {
        orderDataBaseAccess.update(data);
        return data;
    }


    @Override
    public ArrayList<Order> getAll() {
        return orderDataBaseAccess.getAllOrders();
    }
}
