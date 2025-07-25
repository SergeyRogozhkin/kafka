package org.example.notifications.config;

import org.example.notifications.NotificationWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(notificationHandler(), "/ws/notifications")
                .setAllowedOriginPatterns("*");
    }

    @Bean
    public TextWebSocketHandler notificationHandler() {
        return new NotificationWebSocketHandler();
    }
}
