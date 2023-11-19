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
        isHost = false;
        initHostsLayout(task.getUsers());
        if (isHost){
            configureForHost();
        }else{
            configureForExecutor();
        }
    }

//    public PublicTaskElement(PublicTask task) {
//        super(task);
//        publicTask = task;
//        initHostsLayout(task.getUsers());
//        configureForExecutor();
//    }

    private void configureForHost(){
        initExecutorsLayout(publicTask.getPublicSubtasks());
//        initUsersAvatars(task.getUsers());
        initSubtasksAccordion(publicTask.getPublicSubtasks());

        super.configureShowMoreButton();
        super.configureBackButton();
        getFirstButtonLayout().add(getShowMoreButton());
        getSecondButtonLayout().add(getBackButton());

        configureFirstLayout();
        getTaskLayout().add(getFirstLayout());
        getTaskLayout().add(getFirstButtonLayout());

        configureSecondLayout();
    }

    private void configureForExecutor(){
        initExecutorsLayout(publicTask.getPublicSubtasks());
//        initUsersAvatars(task.getUsers());
        initSubtasksAccordion(publicTask.getPublicSubtasks());

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
        for (User user: users
             ) {
            if (user.getUsername().equals(username)) {
                isHost = true;
                break;
            }
        }
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
