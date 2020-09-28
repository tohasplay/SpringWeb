package com.demo.businesscore.customer;

import com.demo.businesscore.order.Order;
import com.demo.utils.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CustomerManagerTest {

    @Mock
    Customer customer;

    CustomerManager customerManager = new CustomerManager();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void calculateTotal() {
        Mockito.when(customer.getOrders()).thenReturn(
                new ArrayList<>(){{
                    add(new Order(1, "some", 20f));
                    add(new Order(2, "some", 30f));
                    add(new Order(3, "some", 35.5f));
                }}
        );
        assertEquals(new Node<Float, Integer>(85.5f, 3), customerManager.calculateTotal(customer));
    }
}