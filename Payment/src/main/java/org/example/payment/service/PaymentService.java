package org.example.payment.service;

import org.example.payment.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final KafkaOrderProducer kafkaOrderProducer;

    public PaymentService(KafkaOrderProducer kafkaOrderProducer) {
        this.kafkaOrderProducer = kafkaOrderProducer;
    }

    @KafkaListener(topics = "new_orders", groupId = "payment-group", containerFactory = "kafkaListenerContainerFactory")
    public void consumeNewOrder(Order order) {
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