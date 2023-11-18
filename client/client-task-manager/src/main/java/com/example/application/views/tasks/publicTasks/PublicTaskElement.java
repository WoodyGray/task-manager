package com.example.application.views.tasks.publicTasks;

import com.example.application.components.TaskElement;
import com.example.application.data.PublicSubtask;
import com.example.application.data.PublicTask;
import com.example.application.data.User;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.avatar.AvatarGroup;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.util.List;

public class PublicTaskElement extends TaskElement {
    private AvatarGroup hostsAvatars;
    private AvatarGroup executorsAvatars;
    private PublicTask publicTask;
    private Accordion subtasksAccordion;
    private HorizontalLayout hostsLayout;
    private HorizontalLayout executorsLayout;
    private String username;
    private boolean isHost;

    public PublicTaskElement(PublicTask task, String username) {
        super(task, username);
        publicTask = task;
        initHostsLayout(task.getUsers());
        if (isHost)
        initExecutorsLayout(task.getPublicSubtasks());
//        initUsersAvatars(task.getUsers());
        initSubtasksAccordion(task.getPublicSubtasks());

        super.configureShowMoreButton();
        super.configureBackButton();
        getFirstButtonLayout().add(getShowMoreButton());
        getSecondButtonLayout().add(getBackButton());

        configureFirstLayout();
        getTaskLayout().add(getFirstLayout());
        getTaskLayout().add(getFirstButtonLayout());

        configureSecondLayout();
    }

    public void addAvatarsToGroup(List<User> users, AvatarGroup group){
        if (users != null) {
            for (User user : users
            ) {
                AvatarGroup.AvatarGroupItem avatar =
                        new AvatarGroup.AvatarGroupItem(user.getUsername());
                group.add(avatar);
            }
        }
    }

    public void initHostsLayout(List<User> users){
        hostsAvatars = new AvatarGroup();
        addAvatarsToGroup(users, hostsAvatars);
        Paragraph hosts = new Paragraph("hosts:");
        hostsLayout = new HorizontalLayout(hosts, hostsAvatars);
    }

    public void initExecutorsLayout(List<PublicSubtask> subtasks){
        executorsAvatars = new AvatarGroup();
        Paragraph users = new Paragraph("executors:");
        for (PublicSubtask subtask: subtasks
             ) {
            addAvatarsToGroup(subtask.getUsers(), executorsAvatars);
        }
        executorsLayout = new HorizontalLayout(users, executorsAvatars);
    }

    public void initSubtasksAccordion(List<PublicSubtask> subtasks){
        subtasksAccordion = new Accordion();
        subtasksAccordion.close();
        subtasksAccordion.add("subtasks",
                new PublicSubtasksAccordion(subtasks));
    }

    @Override
    public void configureFirstLayout() {
        super.configureFirstLayout();
//        getFirstLayout().add(getDescription());
//        getFirstLayout().add(getDeadLine());
        getFirstLayout().add(getFirstButtonLayout());
    }

    @Override
    public void configureSecondLayout() {
        super.configureSecondLayout();
        getSecondLayout().add(
                subtasksAccordion,
                hostsLayout,
                executorsLayout,
                getSecondButtonLayout()
        );
    }


}
