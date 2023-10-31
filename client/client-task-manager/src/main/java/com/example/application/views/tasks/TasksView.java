package com.example.application.views.tasks;

import com.example.application.components.ThemeButton;
import com.example.application.data.PersonalSubtask;
import com.example.application.data.PersonalTask;
import com.example.application.data.PublicSubtask;
import com.example.application.data.PublicTask;
import com.example.application.services.CrmServiceRest;
import com.example.application.views.authentication.SignUpForm;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import java.util.ArrayList;
import java.util.List;

@Route("tasks")
@PageTitle("Sign-up | Vaadin CRM")
public class TasksView extends AppLayout {

    private CrmServiceRest service;

    public TasksView(CrmServiceRest crmServiceRest){
        service = crmServiceRest;
        DrawerToggle toggle = new DrawerToggle();
        H1 title = new H1("Task manager");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        Tabs tabs = getTabs();

        addToDrawer(tabs);
        addToNavbar(toggle, title, ThemeButton.configureThemeButton());
//        setContent(new ListView(service));
    }

    private Tabs getTabs(){
        Tabs tabs = new Tabs();
        tabs.add(createTab(VaadinIcon.TASKS, "Tasks"),
                createTab(VaadinIcon.USER, "Account"));
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        return tabs;
    }

    private Tab createTab(VaadinIcon viewIcon, String viewName){
        Icon icon = viewIcon.create();
        icon.getStyle().set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
//        link.add(icon, new Span(viewName));
        link.add(icon);

        switch (viewName){
            case "Tasks":
                link.add(configureTasksButton());
                break;
            case "Account":
                link.add(configureAccountButton());
        }


        link.setTabIndex(-1);

        return new Tab(link);
    }

    private Button configureTasksButton(){
        Button button = new Button("Tasks");
        button.addClickListener(click -> {
            List<PublicTask> publicTasks = service.getPublicTasks();
            List<PersonalTask> personalTasks = service.getPersonalTasks();
            if (personalTasks == null)
                personalTasks = new ArrayList<>();

            if (publicTasks == null)
                publicTasks = new ArrayList<>();

            setContent(new TasksTabs(
                    publicTasks,
                    personalTasks
            ));
        });
        return button;
    }

    private Button configureAccountButton(){
        Button button = new Button("Account");
        button.addClickListener(click -> setContent(new SignUpForm()));
        return button;
    }
}