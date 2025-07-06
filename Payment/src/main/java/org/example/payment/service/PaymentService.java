package org.example.payment.service;

import lombok.extern.slf4j.Slf4j;
import org.example.payment.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentService {

    private final KafkaOrderProducer kafkaOrderProducer;


    public PaymentService(KafkaOrderProducer kafkaOrderProducer) {
        this.kafkaOrderProducer = kafkaOrderProducer;
    }

    @KafkaListener(topics = "${kafka.topic.new_orders}", groupId = "payment-group", containerFactory = "kafkaListenerContainerFactory")
    public void consumeNewOrder(Order order) {
        log.info("Received order: {}", order);
        System.out.println("Получен заказ: " + order.getId());

        boolean paymentSuccess = processPayment(order);

        if (paymentSuccess) {
            order.setPaid(true);
            kafkaOrderProducer.sendPaidOrder(order);//отправляем
            System.out.println("Оплата прошла успешно, заказ обработан");//логируем
        } else {
            System.out.println("Оплата не удалась для заказа: " + order.getId());
            //логика на случай, если оплата не прошла
        }
    }

    //логика оплаты
    private boolean processPayment(Order order) {
        return true;
    }
}