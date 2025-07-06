package org.example.orders.service;

import org.example.orders.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final KafkaOrderProducer orderProducer;

    @Autowired
    public OrderService(KafkaOrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    public void create(Order order) {
        orderProducer.send(order);
        //не стал усложнять
        // с принятием и возвратом Dto
    }
}

