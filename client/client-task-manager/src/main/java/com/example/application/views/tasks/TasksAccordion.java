package com.example.application.views.tasks;

import com.example.application.data.Task;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class TasksAccordion extends Accordion {
    private VerticalLayout buttonLayout = new VerticalLayout();
    private Button taskInfoButton;
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
            taskInfoButton = new Button("Show more");
            buttonLayout.add(taskInfoButton);

            VerticalLayout taskLayout = new VerticalLayout(
                    description,
                    deadLine,
                    buttonLayout);
            taskLayout.setSpacing(false);
            taskLayout.setPadding(false);

            this.add(task.getTaskName(), taskLayout);
        }
    }
}
