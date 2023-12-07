package com.woody.task_manager.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationThread extends Thread {
    @Autowired
    private NotificationController notificationController;

    @Override
    public void run() {
        System.out.println("Поток начал выполнение.");

        try {
            while (true) {
                Message message = new Message();
                message.setTo("whalter");
                message.setText("cook meth");
                notificationController.sendToSpecificUser(message);
                // Задержка на 5 секунд (5000 миллисекунд)
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Поток завершил выполнение.");
    }
}
