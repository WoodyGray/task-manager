package com.woody.task_manager.notification;

import com.woody.task_manager.util.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class NotificationController {

    private final SimpMessagingTemplate messagingTemplate;
    private JwtTokenUtils jwtTokenUtils;

    public NotificationController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/app/task-notifications")
    public void handleNotification(@Header("Authorization") String authorization, String message) {
        log.info("Received notification: {} with token: {}", message, authorization);

        // Получите пользователя из токена (здесь нужна ваша логика)
        String username = extractUsernameFromToken(authorization);

        // Отправьте уведомление только конкретному пользователю
        messagingTemplate.convertAndSendToUser(username, "/topic/task-notifications", message);
    }

    // Метод для извлечения имени пользователя из токена
    private String extractUsernameFromToken(String token) {
        // Ваша логика извлечения имени пользователя из токена
        // Например, с использованием библиотеки jjwt
        return jwtTokenUtils.getUsername(token); // Верните имя пользователя
    }
}
