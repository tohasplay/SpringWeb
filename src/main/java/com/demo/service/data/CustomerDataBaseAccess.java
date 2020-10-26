package com.demo.service.data;

import com.demo.dto.Customer;
import com.demo.dto.Order;

import java.util.ArrayList;
import java.util.List;

public interface CustomerDataBaseAccess {
    List<Customer> getAll();
    Customer findById(long id);
    Customer put(Customer data);
    void delete(long id);
    void update(long id, Customer data);
    ArrayList<Order> getOrders(long id);
}
