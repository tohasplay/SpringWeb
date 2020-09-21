package com.demo.service.customers;

import com.demo.utils.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/customer")
public class CustomersController {

    private final CustomerInstance customers;

    private CustomersController(@Autowired CustomerInstance customers) {
        this.customers = customers;
    }

    @GetMapping
    public ArrayList<Customer> defaultMap() {
        return customers.getAllCustomers();
    }

    @GetMapping("{id}")
    public Customer getById(@PathVariable long id) {
        return customers.getCustomerById(id);
    }

    @GetMapping("/count/{id}")
    public Node<Float, Integer> getCount(@PathVariable long id) {
        return customers.getChek(id);
    }


    @PostMapping
    public Customer register(@RequestBody Customer customer) {
        return customers.addCustomer(customer);
    }
}
