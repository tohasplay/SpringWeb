package com.demo.service.controller;

import com.demo.businesscore.order.Order;
import com.demo.service.presentor.OrderUndefinedPresentor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderUndefinedPresentor orderPresentor;

    private OrderController(@Autowired OrderUndefinedPresentor orderPresentor){
        this.orderPresentor = orderPresentor;
    }

    @GetMapping
    public ArrayList<Order> defaultMapping(){
        return orderPresentor.getAll();
    }

    @PostMapping("{userId}")
    public Order createOrder(@RequestBody postOrderRequestData data, @PathVariable long userId){

        return orderPresentor.add(new Order(0, data.getText(), 0f), userId, data.getPassword());
    }

    @GetMapping("{id}")
    public Order getOrder(@PathVariable long id){
        return orderPresentor.get(id);
    }

}
