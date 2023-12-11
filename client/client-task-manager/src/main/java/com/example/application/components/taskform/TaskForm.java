package com.example.application.components.taskform;

import com.example.application.services.CrmServiceRest;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.value.ValueChangeMode;
import lombok.Data;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
public class TaskForm extends VerticalLayout {
    private CrmServiceRest service;
    private TextArea taskName;
    private TextArea description;
    private DateTimePicker deadline;
    public Button addTaskButton;


    public TaskForm(CrmServiceRest service){
        this.service = service;
        configureTaskNameArea();
        configureTaskDescArea();
        configureTaskDeadline();
        addTaskButton = new Button("Add");
        add(taskName, description, deadline, addTaskButton);
    }

    public void configureTaskDeadline(){
        deadline = new DateTimePicker();
        deadline.setLabel("Deadline of task:");
        deadline.setHelperText("Must be later than today");

        deadline.setMin(LocalDateTime.now());
        deadline.addValueChangeListener(event -> {
            LocalDateTime value = deadline.getValue();
            String errorMessage = null;
            if (value != null) {
                if (value.compareTo(deadline.getMin()) < 0) {
                    errorMessage = "Too early, choose another date";
                }
            }
            deadline.setErrorMessage(errorMessage);
        });
    }

    public void configureTaskNameArea(){
        taskName = new TextArea();
        taskName.setLabel("Task name");
        taskName.setMinLength(5);
        taskName.setMaxLength(100);
        taskName.setValueChangeMode(ValueChangeMode.EAGER);
        taskName.setRequiredIndicatorVisible(true);
        taskName.addValueChangeListener(e -> {
            e.getSource()
                    .setHelperText(e.getValue().length() + "/" + 100);
        });
    }

    public void configureTaskDescArea(){
        description = new TextArea();
        description.setLabel("Description of task:");
    }
    public void configureAddTaskButton(){
    }

    public boolean generateTask(){
        return true;
    }
}
