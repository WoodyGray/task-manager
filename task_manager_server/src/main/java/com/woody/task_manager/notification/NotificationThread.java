package com.woody.task_manager.notification;

public class NotificationThread extends Thread {
    @Override
    public void run() {
        System.out.println("Поток начал выполнение.");

        try {
            // Задержка на 5 секунд (5000 миллисекунд)
            Thread.sleep(5000);
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
