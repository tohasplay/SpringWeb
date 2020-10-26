package com.demo.service.presentor;

import com.demo.dto.Customer;
import com.demo.utils.Node;

import java.util.ArrayList;

public interface CustomerPresentor {
    Node<Float, Integer> getChek(long id);
    Customer add(Customer data);
    Customer get(long id);
    void delete(long id);
    Customer update(long id, Customer data);
    ArrayList<Customer> getAll();
}
