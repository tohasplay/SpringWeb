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
//            producer.sendMessage("registration -> "
//                    + customer.toString()
//            );
//      1 produce msg about new order ->
//      2 consume that msg try to create order and push it to db
//      3a on success produce msg about successful operation ->
//      3b on fall redirect msg to exception channel (invalid msg topic) ->
//      4 consume msg from (3a || 3b) return result(exc)
        return customers.add(customer);
    }
}