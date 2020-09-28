package com.demo.service.presentor;

import com.demo.businesscore.order.Order;
import com.demo.exception.NotFoundException;
import com.demo.service.data.OrderDataBaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OrderPresentor implements OrderUndefinedPresentor {

    private OrderDataBaseAccess orderDataBaseAccess;

    private OrderPresentor(@Autowired OrderDataBaseAccess orderDataBaseAccess){
        this.orderDataBaseAccess = orderDataBaseAccess;
    }

    @Override
    public Order add(Order data, long id) {
        orderDataBaseAccess.putObject(data, id);
        return data;
    }

    @Override
    public Order get(long id) {
        return orderDataBaseAccess.getAllData().stream().filter(
                order -> order.getId() == id
        ).findFirst().orElseThrow(NotFoundException::new);
    }

    @Override
    public void delete(Order data, long id) {
        orderDataBaseAccess.deleteObject(data, id);
    }

    @Override
    public Order update(Order data, long id) {
        orderDataBaseAccess.updateObject(data, id);
        return data;
    }

    @Override
    public ArrayList<Order> getAll() {
        return (ArrayList<Order>) orderDataBaseAccess.getAllData();
    }
}
