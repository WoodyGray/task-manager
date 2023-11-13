package com.example.application.components;

import com.example.application.data.Task;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.Data;

@Data
public class TaskElement<T extends Task>  {
    private Task task;
    private Span description;
    private Span deadLine;
    private VerticalLayout taskLayout;
    private Button showMoreButton;
    private HorizontalLayout firstButtonLayout;
    private HorizontalLayout secondButtonLayout;

    public TaskElement(T task) {
        this.task = task;
        description = new Span("Description: "
                + task.getDescription());
        deadLine = new Span("Deadline: "
                + task.getDeadline());
        taskLayout = new VerticalLayout(
                description,
                deadLine);

        taskLayout.setSpacing(false);
        taskLayout.setPadding(false);
    }



    public <T extends Task> Button configureShowMoreButton(T task, VerticalLayout taskLayout){
        Button showMoreButton =  new Button("Show more");
//        showMoreButton.addClickListener(event -> {
//            taskLayout.remove(firstButtonLayout);
//
//
//        });
        return showMoreButton;
    }
}
