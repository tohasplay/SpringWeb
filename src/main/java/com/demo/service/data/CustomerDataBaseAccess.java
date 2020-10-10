package com.demo.service.data;

import com.demo.dto.Customer;
import com.demo.dto.Order;

import java.util.ArrayList;

public interface CustomerDataBaseAccess extends DataBaseAccess<Customer> {
    ArrayList<Order> getOrders(long id);
}
