package com.woody.task_manager.notification;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendNotification(String username, String message) {
        Notification notification = new Notification(username, message);
        messagingTemplate.convertAndSendToUser(username, "/topic/task-notifications", notification);
    }
}
