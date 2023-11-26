package com.example.application.notification;

import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationListener {

    @MessageMapping("/app/task-notifications")
    @SendToUser("/topic/task-notifications")
    public String handleNotification(@Payload String message) {
        log.info("Received notification: {}", message);
        return message;
    }
}

