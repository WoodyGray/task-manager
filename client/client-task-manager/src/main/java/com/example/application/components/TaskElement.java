package com.example.application.components;

import com.example.application.data.Task;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.Data;

@Data
public class TaskElement<T extends Task>  {
    private T task;
    private Span description;
    private Span deadLine;
    private VerticalLayout taskLayout;
    private Button showMoreButton;
    private Button backButton;
    private VerticalLayout firstLayout;
    private VerticalLayout secondLayout;
    private HorizontalLayout firstButtonLayout;
    private HorizontalLayout secondButtonLayout;

    public TaskElement(T task) {
        this.task = task;
        description = new Span("Description: "
                + task.getDescription());
        deadLine = new Span("Deadline: "
                + task.getDeadline());
//        configureFirstLayout();
//        configureSecondLayout();

        taskLayout = new VerticalLayout();

        taskLayout.setSpacing(false);
        taskLayout.setPadding(false);
    }

    private void configureShowMoreButton(){
        Button showMoreButton =  new Button("Show more");
        showMoreButton.addClickListener(event -> {
            taskLayout.remove(firstLayout);
            taskLayout.add(secondLayout);
        });
        this.showMoreButton = showMoreButton;
    }

    private void configureBackButton(){
        Button backButton = new Button("Back");
        backButton.addClickListener(event -> {
            taskLayout.remove(secondLayout);
            taskLayout.add(firstLayout);
        });
        this.backButton = backButton;
    }

    public void configureFirstLayout(){
//        description = new Span("Description: "
//                + task.getDescription());
//        deadLine = new Span("Deadline: "
//                + task.getDeadline());
        configureShowMoreButton();
        firstButtonLayout = new HorizontalLayout(
                showMoreButton
        );
        firstLayout = new VerticalLayout();
        firstLayout.add(description, deadLine);
    }

    public void configureSecondLayout(){
//        description = new Span("Description: "
//                + task.getDescription());
//        deadLine = new Span("Deadline: "
//                + task.getDeadline());
        configureBackButton();
        secondButtonLayout = new HorizontalLayout(
                backButton
        );
        secondLayout = new VerticalLayout();
        secondLayout.add(description, deadLine);
    }
}
