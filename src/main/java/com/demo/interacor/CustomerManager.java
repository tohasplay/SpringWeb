package com.demo.interacor;

import com.demo.businesscore.Order;
import com.demo.businesscore.OrderCreator;
import com.demo.exception.NotFoundException;
import com.demo.service.data.OrderDataBaseAccess;
import com.demo.utils.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CustomerManager {

    private final OrderDataBaseAccess orderDataBaseAccess;

    @Autowired
    private CustomerManager(OrderDataBaseAccess orderDataBaseAccess) {
        this.orderDataBaseAccess = orderDataBaseAccess;
    }
    public Order makeOrder(long id, String text, String password) {
        if (!orderDataBaseAccess.verifyUser(id, password))
            throw new NotFoundException();
        OrderCreator orderCreator = new OrderCreator();
        orderDataBaseAccess.putObject(orderCreator.createOrder(text), id);

        return orderDataBaseAccess.getLastOrder();
    }


    public Node<Float, Integer> calculateTotal(long id) {
        Node<Float, Integer> result = new Node<>(0f, 0);
        for (Order o :
                orderDataBaseAccess.getOrdersByCustomer(id)) {
            result.first += o.getPrice();
            result.second++;
        }
        return result;
    }
}
