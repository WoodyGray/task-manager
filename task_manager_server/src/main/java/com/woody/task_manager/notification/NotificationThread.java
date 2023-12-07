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
            // Задержка на 5 секунд (5000 миллисекунд)
            while (true){
                Message message = new Message();
                message.setTo("whalter");
                message.setText("lets start cook");
                notificationController.sendToSpecificUser(message);
                System.out.println(1);
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Поток завершил выполнение.");
    }

    public static void main(String[] args) {
        // Создание экземпляра потока
        NotificationThread myThread = new NotificationThread();

        // Запуск потока
        myThread.start();
    }
}
