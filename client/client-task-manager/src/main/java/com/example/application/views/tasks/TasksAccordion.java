package com.example.application.views.tasks;

import com.example.application.data.Task;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

public class TasksAccordion extends Accordion {
    public TasksAccordion(List<Task> tasks){
        close();
        addTasks(tasks);
    }

    private void addTasks(List<Task> tasks){
        for (Task task: tasks
             ) {
            Span description = new Span("Description: "
                    + task.getDescription());
            Span deadLine = new Span("Deadline: "
                    + task.getDeadline());
            Button taskInfoButton = new Button("Show more");

            VerticalLayout taskLayout = new VerticalLayout(
                    description,
                    deadLine,
                    taskInfoButton);
            taskLayout.setSpacing(false);
            taskLayout.setPadding(false);

            this.add(task.getTaskName(), taskLayout);
        }
    }
}
