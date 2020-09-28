package com.demo.service.data;

import com.demo.businesscore.customer.Customer;
import com.demo.businesscore.customer.CustomerManager;
import com.demo.businesscore.order.Order;
import com.demo.interacor.OrderLister;
import com.demo.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OrderDataBase implements OrderDataBaseAccess {

    private final OrderLister orderLister;
    private final CustomerManager customerManager;
    private final DataBaseAccess<Customer> customerDataBaseAccess;

    private OrderDataBase(@Autowired OrderLister orderLister,
                          @Autowired DataBaseAccess<Customer> customerDataBaseAccess,
                          @Autowired CustomerManager customerManager) {
        this.orderLister = orderLister;
        this.customerDataBaseAccess = customerDataBaseAccess;
        this.customerManager = customerManager;
    }

    @Override
    public ArrayList<Order> getAllData() {
        return orderLister.listAllOrders();
    }

    @Override
    public Order findById(long id) {
        return orderLister.listAllOrders()
                .stream().filter(order -> order.getId() == id)
                .findFirst().orElseThrow(NotFoundException::new);
    }

    @Override
    public void putObject(Order data, long id) {
        customerManager.makeOrder(customerDataBaseAccess.findById(id), data.getText());
    }

    @Override
    public void deleteObject(Order data, long id) {
        Customer customer = customerDataBaseAccess.findById(id);
        customer.getOrders().stream().filter(order -> order.getId() == id).findFirst()
                .ifPresent(
                        customer.getOrders()::remove
                );
    }

    @Override
    public void updateObject(Order data, long id) {
         customerDataBaseAccess.findById(id).getOrders().stream().filter(
                order -> order.getId() == data.getId()
        ).findFirst().ifPresent(
                order -> {
                    order.setText(data.getText());
                    order.setPrice(data.getPrice());
                }

        );
    }

    @Override
    public boolean contains(Order data, long id) {
        return customerDataBaseAccess.findById(id).getOrders().stream().anyMatch(order -> order.getId() == id);
    }


}
