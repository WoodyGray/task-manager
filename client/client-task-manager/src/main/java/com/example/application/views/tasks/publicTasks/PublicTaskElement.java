package com.example.application.views.tasks.publicTasks;

import com.example.application.components.TaskElement;
import com.example.application.data.PublicTask;
import com.example.application.data.Task;
import com.example.application.data.User;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.avatar.AvatarGroup;

import java.util.ArrayList;
import java.util.List;

public class PublicTaskElement extends TaskElement {
    private AvatarGroup usersAvatars;

    public PublicTaskElement(PublicTask task) {
        super(task);
        initUsersAvatars(task.getUsers());
        configureFirstLayout();
        configureSecondLayout();
        getTaskLayout().add(getFirstLayout());
    }

    public void initUsersAvatars(List<User> users){
        usersAvatars = new AvatarGroup();
        if (users != null) {
            for (User user : users
            ) {
                AvatarGroup.AvatarGroupItem avatar =
                        new AvatarGroup.AvatarGroupItem(user.getUsername());
                usersAvatars.add(avatar);
            }
        }
    }

    @Override
    public void configureFirstLayout() {
        super.configureFirstLayout();
        getFirstLayout().add(getFirstButtonLayout());
    }

    @Override
    public void configureSecondLayout() {
        super.configureSecondLayout();
        getSecondLayout().add(
                usersAvatars,
                getSecondButtonLayout()
        );
    }
}
