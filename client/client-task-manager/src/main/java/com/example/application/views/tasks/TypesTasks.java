package com.example.application.views.tasks;

import com.example.application.data.Task;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class TypesTasks extends Div {
    private final Tab inProcess;
    private final Tab extract;
    private final Tab notStarted;
    private final VerticalLayout content;
    private Span inProcessBadge;
    private Span extractBadge;
    private Span notStartedBadge;

    public TypesTasks(){
        inProcessBadge = createBadge(0);
        extractBadge = createBadge(0);
        notStartedBadge = createBadge(0);
        inProcess = new Tab(new Span("In process"), inProcessBadge);
        extract = new Tab(new Span("Extract"), extractBadge);
        notStarted = new Tab(new Span("Not started"), notStartedBadge);

        Tabs tabs = new Tabs();
        tabs.setAutoselect(false);
        tabs.addSelectedChangeListener(
                event -> setContent(event.getSelectedTab()));
        tabs.add(inProcess, extract, notStarted);

        content = new VerticalLayout();
        content.setSpacing(false);

        add(tabs, content);
    }

    public void setContent(Tab tab){
        content.removeAll();
        if (tab == null){
            return;
        }
        if (tab.equals(inProcess)){
            content.add(new Paragraph("In process tasks"));
        }else if (tab.equals(extract)){
            content.add(new Paragraph("Extract tasks"));
        }else if (tab.equals(notStarted)){
            content.add(new Paragraph("Not started tasks"));
        }
    }

    private Span createBadge(int value) {
        Span badge = new Span(String.valueOf(value));
        badge.getElement().getThemeList().add("badge small contrast");
        badge.getStyle().set("margin-inline-start", "var(--lumo-space-xs)");
        return badge;
    }

    public void setBadge(Span span, int value){
        span.setText(String.valueOf(value));
    }
}
