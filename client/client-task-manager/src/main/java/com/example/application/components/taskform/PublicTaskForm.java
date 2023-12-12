package com.example.application.components.taskform;

import com.example.application.data.PublicTask;
import com.example.application.services.CrmServiceRest;
import com.example.application.services.dto.PublicTaskDto;
import com.vaadin.flow.component.notification.Notification;

import java.time.LocalDateTime;
import java.util.Date;

public class PublicTaskForm extends TaskForm{
    private PublicTaskDto publicTaskDto;


    public PublicTaskForm(CrmServiceRest service) {
        super(service);
        configureAddTaskButton();
    }

    @Override
    public void configureAddTaskButton() {
        addTaskButton.addClickListener(buttonClickEvent -> {
            if (generateTask()){
                if (getService().addPublicTask(publicTaskDto)){
                    successNotification.s;
                }else{
                    errorNotification.open();
                }
            }else{
                errorNotification.open();
            }
        });
    }

    @Override
    public boolean generateTask() {
        publicTaskDto = new PublicTaskDto();
        String value = getTaskName().getValue();
        if (value != null){
            publicTaskDto.setTaskName(value);
            value = getDescription().getValue();
            if (value != null){
                publicTaskDto.setDescription(value);
            }
            value = getDeadline().getValue().toString();
            if (value != null){
                publicTaskDto.setDeadline(value);
            }else {
                return false;
            }
            return true;
        }else{
            return false;
        }
    }
}
