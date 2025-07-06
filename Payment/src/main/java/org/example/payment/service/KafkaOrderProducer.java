package org.example.payment.service;

import org.example.payment.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaOrderProducer {

    private final KafkaTemplate<String, Order> kafkaTemplate;

    @Value("${kafka.topic.payed_orders}")
    private String payedOrdersTopic;

    public KafkaOrderProducer(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPaidOrder(Order order) {
        kafkaTemplate.send(payedOrdersTopic, String.valueOf(order.getId()), order);
        System.out.println("Заказ отправлен в " + payedOrdersTopic + ": " + order.getId());
    }
}

