package com.example.application.components;

import com.example.application.data.Task;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
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
    private String username;

    public TaskElement(T task, String username) {
        this(task);
        this.username = username;

    }

    public TaskElement(T task) {
        this.task = task;
        description = new Span("Description: "
                + task.getDescription());
        deadLine = new Span("Deadline: "
                + task.getDeadline());
//        configureFirstLayout();
//        configureSecondLayout();

        firstButtonLayout = new HorizontalLayout();
        secondButtonLayout = new HorizontalLayout();

        taskLayout = new VerticalLayout();

        taskLayout.setSpacing(false);
        taskLayout.setPadding(false);
    }

    public void configureShowMoreButton(){
        Button showMoreButton =  new Button();
        showMoreButton.setIcon(new Icon(VaadinIcon.CHEVRON_DOWN));
        showMoreButton.addClickListener(event -> {
//            taskLayout.remove(firstLayout);
            taskLayout.remove(firstButtonLayout);
            taskLayout.add(secondLayout);
            taskLayout.add(secondButtonLayout);
        });
        this.showMoreButton = showMoreButton;
    }

    public void configureBackButton(){
        Button backButton = new Button();
        backButton.setIcon(new Icon(VaadinIcon.CHEVRON_UP));
        backButton.addClickListener(event -> {
            taskLayout.remove(secondLayout);
            taskLayout.remove(secondButtonLayout);
            taskLayout.add(firstButtonLayout);
        });
        this.backButton = backButton;
    }

    public void configureFirstLayout(){
//        description = new Span("Description: "
//                + task.getDescription());
//        deadLine = new Span("Deadline: "
//                + task.getDeadline());
//        configureShowMoreButton();
//        firstButtonLayout = new HorizontalLayout(
//                showMoreButton
//        );
        firstLayout = new VerticalLayout();
        firstLayout.add(description, deadLine);
    }

    public void configureSecondLayout(){
//        description = new Span("Description: "
//                + task.getDescription());
//        deadLine = new Span("Deadline: "
//                + task.getDeadline());
//        configureBackButton();
//        secondButtonLayout = new HorizontalLayout(
//                backButton
//        );
        secondLayout = new VerticalLayout();
//        secondLayout.add(description, deadLine);
    }
}
