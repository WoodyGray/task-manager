package com.example.application.views.tasks.publicTasks;

import com.example.application.components.TaskElement;
import com.example.application.data.PublicSubtask;
import com.example.application.data.Task;
import com.example.application.data.User;
import com.vaadin.flow.component.avatar.AvatarGroup;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.util.List;

public class PublicSubtaskElement extends TaskElement {
    private AvatarGroup executorsAvatars;
    private HorizontalLayout executorsLayout;
    private Span status;

    public PublicSubtaskElement(PublicSubtask task) {
        super(task);
        initExecutorLayout(task.getUsers());
        initStatus(task.getStatus());

        configureFirstLayout();
        getTaskLayout().add(getFirstLayout());
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

    private void initStatus(Integer status){
        if (status == 0){
            this.status = new Span("status: Not started");
        }else if (status == 1){
            this.status = new Span("status: In process");
        }else if (status == 2){
            this.status = new Span("status: Extract");
        }else if (status == 3){
            this.status = new Span("status: Done");
        }else{
            this.status = new Span("");
        }
    }

    private void initExecutorLayout(List<User> users){
        executorsAvatars = new AvatarGroup();
        addAvatarsToGroup(users, executorsAvatars);
        Paragraph executors = new Paragraph("executors:");
        executorsLayout = new HorizontalLayout(executors,
                executorsAvatars);
    }


    @Override
    public void configureFirstLayout() {
        super.configureFirstLayout();
        getFirstLayout().add(
                status,
                executorsLayout
        );
    }

//    @Override
//    public void configureSecondLayout() {
//        super.configureSecondLayout();
//        getSecondLayout().add(
//                usersAvatars,
//                getSecondButtonLayout()
//        );
//    }
}
