package com.woody.task_manager.notification;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskScheduler {

    private final NotificationService notificationService;

    public TaskScheduler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Scheduled(fixedRate = 5000) // пример: каждые 5 секунд
    public void checkOverdueTasks() {
        // Ваша логика проверки просроченных задач
        // ...
        // Пример уведомления пользователя "user1" о просроченной задаче
        notificationService.sendNotification("whalter", "Task is overdue!");
    }
}
