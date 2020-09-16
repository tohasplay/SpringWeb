package com.demo.service;

import com.demo.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/order")
public class OrdersController {

    private int counter = 4;
    private final ArrayList<Order> orders = new ArrayList<>() {
        {
            add(new Order(1, "First", 23.23f, 1));
            add(new Order(2, "Second", 12.0f, 2));
            add(new Order(3, "Third", 123.f, 2));
        }
    };

    @GetMapping
    public ArrayList<Order> defaultMap() {
        return orders;
    }

    @GetMapping("{id}")
    public Order getById(@PathVariable long id) {
        return getOrder(id);
    }

    private Order getOrder(long id) {
        return orders.stream().filter(order -> order.getId() == id).findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Order create(@RequestBody Order order) {
        if (CustomerController.getCustomers().stream()
                .anyMatch(customer -> customer.getId() == order.getCustomer_id())){
        order.setId(counter++);
        order.setPrice(Math.round((float) (5 + Math.random() * 500) * 100.0f) / 100.0f);
        orders.add(order);
        return order;}else {
            throw new NotFoundException();
        }
    }

    @PutMapping("{id}")
    public Order update(@PathVariable long id, @RequestBody Order order) {
        Order orderFromDB = getOrder(id);
        orderFromDB.setText(order.getText());
        return orderFromDB;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        Order order = getOrder(id);
        orders.remove(order);
    }
}
