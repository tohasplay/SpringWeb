package com.demo.service.customers;

import com.demo.service.orders.OrderInstance;
import com.demo.utils.Node;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public interface CustomerInstance {
    ArrayList<Customer> getAllCustomers();
    Customer getCustomerById(long id);
    Customer addCustomer(Customer customer);
    Customer updateCustomer(long id, Customer customer);
    Node<Float, Integer> getChek(long id);
    void deleteCustomer();
}
