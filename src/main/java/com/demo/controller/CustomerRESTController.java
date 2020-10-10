package com.demo.controller;

import com.demo.dto.Customer;
import com.demo.service.kafka.KafkaCustomerProducer;
import com.demo.service.presentor.CustomerPresentor;
import com.demo.utils.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/customer")
public class CustomerRESTController {

    private final CustomerPresentor customers;
    private final KafkaCustomerProducer producer;

    @Autowired
    private CustomerRESTController(CustomerPresentor customers,
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

    //TODO: consistency
    @PostMapping
    public Customer register(@RequestBody Customer customer) {
        producer.sendMessage("registration -> "
                + customer.toString()
        );
        return customers.add(customer);
    }
}
