package com.demo.service.data;

import com.demo.businesscore.Customer;
import com.demo.businesscore.Order;

import java.util.ArrayList;

public interface CustomerDataBaseAccess extends DataBaseAccess<Customer> {
    ArrayList<Order> getOrders(long id);
}
