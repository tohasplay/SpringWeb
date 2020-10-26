package com.demo.controller;

import com.demo.dto.Order;
import com.demo.dto.PostOrderRequestBody;
import com.demo.service.kafka.KafkaOrderProducer;
import com.demo.service.presentor.OrderPresentor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/order")
public class OrderRestController {

    private final OrderPresentor orderPresentor;
    private final KafkaOrderProducer kafkaOrderProducer;

    @Autowired
    public OrderRestController(OrderPresentor orderPresentor,
                                KafkaOrderProducer kafkaOrderProducer) {
        this.orderPresentor = orderPresentor;
        this.kafkaOrderProducer = kafkaOrderProducer;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ArrayList<Order> defaultMapping() {
        return orderPresentor.getAll();
    }

    @PostMapping("{userId}")
    public Order createOrder(@RequestBody PostOrderRequestBody data, @PathVariable long userId) {
        kafkaOrderProducer.sendMessage("msg: " + data.getText() + " | user: " + userId);
        return orderPresentor.add(new Order(0, data.getText(), 0f), userId, data.getPassword());
    }

    @GetMapping("{id}")
    public Order getOrder(@PathVariable long id) {
        return orderPresentor.get(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable long id){orderPresentor.delete(id);}

}
