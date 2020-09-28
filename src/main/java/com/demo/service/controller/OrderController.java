package com.demo.service.controller;

import com.demo.businesscore.order.Order;
import com.demo.service.presentor.OrderUndefinedPresentor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderUndefinedPresentor orderExecutionUnit;

    private OrderController(@Autowired OrderUndefinedPresentor orderExecutionUnit){
        this.orderExecutionUnit = orderExecutionUnit;
    }

    @GetMapping
    public ArrayList<Order> defaultMapping(){
        return orderExecutionUnit.getAll();
    }

    @PostMapping("{userId}")
    public Order createOrder(@RequestBody Order data, @PathVariable long userId){
        return orderExecutionUnit.add(data, userId);
    }

    @GetMapping("{id}")
    public Order getOrder(@PathVariable long id){
        return orderExecutionUnit.get(id);
    }

}
