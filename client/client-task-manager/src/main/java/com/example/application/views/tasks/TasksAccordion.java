package com.example.application.views.tasks;

import com.example.application.data.Task;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class TasksAccordion extends Accordion {
    private HorizontalLayout showMoreButtonLayout = new HorizontalLayout();
    private HorizontalLayout backButtonLayout = new HorizontalLayout();
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
            VerticalLayout taskLayout = new VerticalLayout(
                    description,
                    deadLine);
            taskInfoButton = configureShowMoreButton(task, taskLayout);
            showMoreButtonLayout.add(taskInfoButton);

            taskLayout.add(showMoreButtonLayout);

            taskLayout.setSpacing(false);
            taskLayout.setPadding(false);

            this.add(task.getTaskName(), taskLayout);
        }
    }

    private Button configureShowMoreButton(Task task, VerticalLayout taskLayout){
        Button showMoreButton =  new Button("Show more");
        showMoreButton.addClickListener(event -> {
            taskLayout.remove(showMoreButtonLayout);


        });
        return showMoreButton;
    }
}
