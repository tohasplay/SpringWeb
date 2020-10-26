package com.demo.service.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaOrderProducer {
        private static final Logger logger = LoggerFactory.getLogger(KafkaOrderProducer.class);
        private static final String TOPIC = "order";
        private static final int key = 0;

        @Autowired
        private KafkaTemplate<String, String> kafkaTemplate;

        public void sendMessage(String msg){
            logger.info(String.format("#### -> Producing message -> %s", msg));
            this.kafkaTemplate.send(TOPIC, String.valueOf(key) ,msg);
            this.kafkaTemplate.flush();
        }
}
