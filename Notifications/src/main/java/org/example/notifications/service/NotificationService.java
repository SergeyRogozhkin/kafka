package org.example.notifications.service;


import lombok.extern.slf4j.Slf4j;
import org.example.notifications.NotificationWebSocketHandler;
import org.example.notifications.model.OrderNotify;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class NotificationService {

    private final NotificationWebSocketHandler webSocketHandler;

    public NotificationService(NotificationWebSocketHandler handler) {
        this.webSocketHandler = handler;
    }

    @KafkaListener(topics = "sent_orders", containerFactory = "kafkaListenerFactory")
    public void listen(OrderNotify notify) {
        log.info("Received order: {}", notify);
        String msg = String.format("Ваш заказ от %s отправлен по адресу: %s",
                notify.getShippingDate(), notify.getDeliveryAddress());
        webSocketHandler.broadcast(msg);
    }
}
