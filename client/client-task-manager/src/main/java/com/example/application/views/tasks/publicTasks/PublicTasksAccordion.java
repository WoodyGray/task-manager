package com.example.application.views.tasks.publicTasks;

import com.example.application.components.TaskElement;
import com.example.application.data.PublicTask;
import com.example.application.data.Task;
import com.example.application.views.tasks.TasksAccordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

public class PublicTasksAccordion extends TasksAccordion {
    public PublicTasksAccordion(List<PublicTask> tasks) {
        super();
        addTasks(tasks, PublicTaskElement.class);
    }

}
