package com.demo.service;

import com.demo.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private static long counter = 1;
    private final static ArrayList<Customer> customers = new ArrayList<>(){{
        add(new Customer(counter++, "Patrik"));
        add(new Customer(counter++, "Adam"));
        add(new Customer(counter++, "Rubin"));
    }};

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    @GetMapping
    public ArrayList<Customer> defaultMap(){
        return customers;
    }

    @GetMapping("{id}")
    public Customer getById(@PathVariable long id){
        return getCustomer(id);
    }

    private Customer getCustomer(long id) {
        return customers.stream().filter(customer -> customer.getId() == id).findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Customer register(@RequestBody Customer customer){
        customer.setId(counter++);
        customers.add(customer);
        return customer;
    }
}
