package com.demo.service.orders;

import com.demo.exception.NotFoundException;
import com.demo.service.customers.CustomersHandler;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OrdersHandler implements OrderInstance{


    private int counter = 4;
    private static final  @Getter
    ArrayList<Order> orders = new ArrayList<>() {
        {
            add(new Order(1, "First", 23.23f, 1));
            add(new Order(2, "Second", 12.0f, 2));
            add(new Order(3, "Third", 123.f, 2));
        }
    };


    @Override
    public ArrayList<Order> getAllOrders() {
        return orders;
    }

    @Override
    public Order getOrderById(long id) {
        return getOrder(id);
    }

    private Order getOrder(long id) {
        return orders.stream().filter(order -> order.getId() == id).findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Order addOrder(Order order) {
        if (CustomersHandler.containsId(order.getCustomer_id())) {
            order.setId(counter++);
            order.setPrice(Math.round((float) (5 + Math.random() * 500) * 100.0f) / 100.0f);
            orders.add(order);
            return order;
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public Order updateOrder(Order order, long id) {
        Order orderFromDB = getOrder(id);
        orderFromDB.setText(order.getText());
        return orderFromDB;
    }

    @Override
    public void deleteOrder(long id) {
        Order order = getOrder(id);
        orders.remove(order);
    }
}
