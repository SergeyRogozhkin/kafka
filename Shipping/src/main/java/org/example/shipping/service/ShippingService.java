package org.example.shipping.service;

import org.example.shipping.model.Order;
import org.example.shipping.model.OrderNotify;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ShippingService {

    private final KafkaTemplate<String, OrderNotify> kafkaTemplate;

    public ShippingService(KafkaTemplate<String, OrderNotify> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "payed_orders", groupId = "shipping-group", containerFactory = "orderKafkaListenerContainerFactory")
    public void consumeOrder(Order order) {
        if (order.isPaid()) {
            System.out.println("Заказ оплачен, начинаем отгрузку: " + order.getId());

            OrderNotify notification = new OrderNotify(
                    LocalDate.now(),
                    generateAddressForOrder(order)
            );

            kafkaTemplate.send("sent_orders", notification);
            System.out.println("Отправлено уведомление об отгрузке заказа: " + order.getId());
        } else {
            System.out.println("Получен неоплаченный заказ! Пропуск: " + order.getId());
        }
    }

    private String generateAddressForOrder(Order order) {
        return "г. Москва, ул. Примерная, д. 54, кв. 1" + order.getId();
    }
}

