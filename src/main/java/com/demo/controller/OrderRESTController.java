package com.demo.controller;

import com.demo.dto.Order;
import com.demo.dto.PostOrderRequestBody;
import com.demo.service.kafka.KafkaOrderProducer;
import com.demo.service.presentor.OrderPresentor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/order")
public class OrderRESTController {

    private final OrderPresentor orderPresentor;
    private final KafkaOrderProducer kafkaOrderProducer;

    @Autowired
    private OrderRESTController(OrderPresentor orderPresentor,
                                KafkaOrderProducer kafkaOrderProducer) {
        this.orderPresentor = orderPresentor;
        this.kafkaOrderProducer = kafkaOrderProducer;
    }

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

}
