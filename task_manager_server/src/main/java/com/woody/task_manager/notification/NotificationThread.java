package com.woody.task_manager.notification;

import com.woody.task_manager.dto.MessageDto;
import com.woody.task_manager.entity.PublicTask;
import com.woody.task_manager.entity.User;

import com.woody.task_manager.service.TaskService;
import com.woody.task_manager.service.UserService;
import com.woody.task_manager.service.WebPushService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
public class NotificationThread extends Thread{
    @Autowired
    private WebPushService webPushService;
    @Autowired
    private TaskService taskService;

    private int cntOfExtractTasks;

    @Override
    public void run() {
        System.out.println("notification thread started");
        try {
            while (true) {
                cntOfExtractTasks = 0;
                checkTasksOnExtract();
                System.out.println(
                        "count of extract tasks: "
                                + cntOfExtractTasks
                );
                Thread.sleep(60000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkTasksOnExtract(){
        checkPublicTasks();
//        checkPublicSubtasks();
//        checkPersonalTasks();
//        checkPersonalSubtasks();
    }

    @Transactional
    private void checkPublicTasks(){
        LocalDateTime currentDate = LocalDateTime.now();
        taskService.getAllNoExtractPublicTasks().forEach(publicTask -> {
            if (currentDate.isAfter(publicTask.getDeadline())){
                publicTask.setStatus(2);
                taskService.saveTask(publicTask);
                cntOfExtractTasks++;
                notifyUsers(publicTask);
            }
        });
    }

    @Transactional
    private void notifyUsers(PublicTask publicTask){
//        Hibernate.initialize(publicTask.getUsers());

        publicTask.getUsers().forEach(user -> {
            user.getSubscriptions()
                    .forEach(entity -> {
                        MessageDto message = new MessageDto(
                                "Your task is overdue",
                                "task name:"
                                        + publicTask.getTaskName()
                                        + "; dead line: " + publicTask.getDeadline()
                        );
                        webPushService.sendNotification(
                                entity,
                                message
                        );
                    });
        });
    }
}
