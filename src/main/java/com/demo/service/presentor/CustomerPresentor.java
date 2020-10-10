package com.demo.service.presentor;

import com.demo.dto.Customer;
import com.demo.utils.Node;

public interface CustomerPresentor extends Presentor<Customer> {
    Node<Float, Integer> getChek(long id);
}
