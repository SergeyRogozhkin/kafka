package org.example.orders.service;

import org.example.orders.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaOrderProducer {

    private final KafkaTemplate<String, Order> kafkaTemplate;

    @Value("${app.kafka.topic}")
    private String topic;

    @Autowired
    public KafkaOrderProducer(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Order order) {
        kafkaTemplate.send(topic, order);
    }
}
