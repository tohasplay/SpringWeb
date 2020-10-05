package com.demo.service.controller;

import com.demo.businesscore.Customer;
import com.demo.service.kafka.KafkaCustomerProducer;
import com.demo.service.presentor.CustomerUndefinedPresentor;
import com.demo.utils.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerUndefinedPresentor<Customer> customers;
    private final KafkaCustomerProducer producer;

    @Autowired
    private CustomerController( CustomerUndefinedPresentor<Customer> customers,
                                KafkaCustomerProducer producer) {
        this.customers = customers;
        this.producer = producer;
    }

    @GetMapping
    public ArrayList<Customer> defaultMap() {
        return customers.getAll();
    }

    @GetMapping("{id}")
    public Customer getById(@PathVariable long id) {
        return customers.get(id);
    }

    @GetMapping("/count/{id}")
    public Node<Float, Integer> getCount(@PathVariable long id) {
        return customers.getChek(id);
    }


    @PostMapping
    public Customer register(@RequestBody Customer customer) {
        producer.sendMessage("registration -> "
                + customer.toString()
        );
        return customers.add(customer);
    }
}
