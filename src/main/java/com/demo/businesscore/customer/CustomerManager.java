package com.demo.businesscore.customer;

import com.demo.businesscore.order.Order;
import com.demo.businesscore.order.OrderCreator;
import com.demo.utils.Node;
import org.springframework.stereotype.Component;


@Component
public class CustomerManager {

    public Order makeOrder(Customer customer, String text){
        OrderCreator orderCreator = new OrderCreator();
        return orderCreator.createOrder(customer, text);
    }

    public Node<Float, Integer> calculateTotal(Customer customer){
        Node<Float, Integer> result = new Node<>(0f,0);
        for (Order o :
                customer.getOrders()) {
            result.first += o.getPrice();
            result.second++;
        }
        return result;
    }
}
