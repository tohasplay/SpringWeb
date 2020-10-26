package com.demo.service.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaCustomerProducer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaOrderProducer.class);
    private static final String TOPIC = "customer";
    private static final int key = 0;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String msg){
        logger.info(String.format("#### -> Producing message -> %s", msg));
        kafkaTemplate.send(TOPIC, String.valueOf(key), msg);
        kafkaTemplate.flush();
    }

}
