package com.example.application.views.tasks;

import com.example.application.data.PersonalTask;
import com.example.application.data.PublicTask;
import com.example.application.views.tasks.personalTasks.TypesPersonalTasks;
import com.example.application.views.tasks.publicTasks.TypesPublicTasks;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

import java.util.List;

public class TasksTabs extends Div {
    private Div typesPublicTasks;
    private Div typesPersonaTasks;
    private final Tab publicTasks;
    private final Tab personalTasks;
    private final VerticalLayout content;

    public TasksTabs(List<PublicTask> publicTaskList,
                     List<PersonalTask> personalTaskList,
                     String username) {
        publicTasks = new Tab(new Span("Public tasks"),
                createBadge(publicTaskList.size()));
        personalTasks = new Tab(new Span("Personal tasks"),
                createBadge(personalTaskList.size()));

        typesPersonaTasks = new TypesPersonalTasks(personalTaskList);
        typesPublicTasks = new TypesPublicTasks(publicTaskList, username);

        Tabs tabs = new Tabs();
        tabs.setAutoselect(false);
        tabs.addSelectedChangeListener(
                event -> setContent(event.getSelectedTab()));
        tabs.add(personalTasks, publicTasks);

        content = new VerticalLayout();
        content.setSpacing(false);

        add(tabs, content);
    }

    private void setContent(Tab tab){
        content.removeAll();
        if (tab == null){
            return;
        }

        if (tab.equals(publicTasks)){
            content.add(typesPublicTasks);
        }else if (tab.equals(personalTasks)){
            content.add(typesPersonaTasks);
        }
    }

    private Span createBadge(int value) {
        Span badge = new Span(String.valueOf(value));
        badge.getElement().getThemeList().add("badge small contrast");
        badge.getStyle().set("margin-inline-start", "var(--lumo-space-xs)");
        return badge;
    }

}