package com.demo.service.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/order")
public class OrdersController {

    private OrdersController(@Autowired OrderInstance orderInstance) {
        orders = orderInstance;
    }

    private final OrderInstance orders;

    @GetMapping
    public ArrayList<Order> defaultMap() {
        return orders.getAllOrders();
    }

    @GetMapping("{id}")
    public Order getById(@PathVariable long id) {
        return orders.getOrderById(id);
    }

    @PostMapping
    public Order create(@RequestBody Order order) {
        return orders.addOrder(order);
    }

    @PutMapping("{id}")
    public Order update(@PathVariable long id, @RequestBody Order order) {
        return orders.updateOrder(order, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        orders.deleteOrder(id);
    }
}
