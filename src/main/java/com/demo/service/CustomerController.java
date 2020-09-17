package com.demo.service;

import com.demo.exception.NotFoundException;
import com.demo.tools.Node;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private static long counter = 1;
    private final static ArrayList<Customer> customers = new ArrayList<>(){{
        add(new Customer(counter++, "Patrik", "ptrk@email.com", "0500450"));
        add(new Customer(counter++, "Adam","adm@email.com", "0500450"));
        add(new Customer(counter++, "Rubin","rbn@email.com", "0500450"));
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

    @GetMapping("/count/{id}")
    public Node<Float, Integer> getCount(@PathVariable long id){
        ArrayList<Order> orders = OrdersController.getOrders().stream().filter(
                order -> order.getCustomer_id() == id
        ).collect(Collectors.toCollection(ArrayList::new));
        Node<Float, Integer> result = new Node<>(0f, 0);
        if (orders.isEmpty()){
            throw new NotFoundException();
        }
        for (Order order:
             orders) {
            result.first += order.getPrice();
            result.second++;
        }
        return result;
    }

    private Customer getCustomer(long id) {
        return customers.stream().filter(customer -> customer.getId() == id).findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Customer register(@RequestBody Customer customer){
        if (customers.contains(customer)){
            return customer;
        }
        customer.setId(counter++);
        customers.add(customer);
        return customer;
    }
}
