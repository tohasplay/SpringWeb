package com.demo.controller;

import com.demo.dto.Customer;
import com.demo.exception.AlreadyRegisteredException;
import com.demo.service.kafka.KafkaCustomerProducer;
import com.demo.service.presentor.CustomerPresentor;
import com.demo.utils.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {

    private final CustomerPresentor customers;
    private final KafkaCustomerProducer producer;

    @Autowired
    public CustomerRestController(CustomerPresentor customers,
                                   KafkaCustomerProducer producer) {
        this.customers = customers;
        this.producer = producer;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ArrayList<Customer> defaultMap() {
        return customers.getAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("{id}")
    public Customer getById(@PathVariable long id) {
        return customers.get(id);
    }

    @GetMapping("/count/{id}")
    public Node<Float, Integer> getCount(@PathVariable long id) {
        return customers.getChek(id);
    }

    //DONE: consistency

    @PostMapping
    public Customer register(@RequestBody Customer customer) {
        try {
            producer.sendMessage("registration -> "
                    + customer.toString()
            );
            return customers.add(customer);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AlreadyRegisteredException("user can't be registered");
        }
    }
}