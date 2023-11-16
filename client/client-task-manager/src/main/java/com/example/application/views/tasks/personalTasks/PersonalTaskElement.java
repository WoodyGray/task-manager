package com.example.application.views.tasks.personalTasks;

import com.example.application.components.TaskElement;
import com.example.application.data.PersonalTask;
import com.example.application.data.Task;

public class PersonalTaskElement extends TaskElement {
    public PersonalTaskElement(PersonalTask task) {
        super(task);
        super.configureShowMoreButton();
        super.configureBackButton();
        getFirstButtonLayout().add(getShowMoreButton());
        getSecondButtonLayout().add(getBackButton());

        configureFirstLayout();
        configureSecondLayout();
        getTaskLayout().add(getFirstLayout());
        getTaskLayout().add(getFirstButtonLayout());
    }

    @Override
    public void configureFirstLayout() {
        super.configureFirstLayout();
        getFirstLayout().add(getFirstButtonLayout());

    }

    @Override
    public void configureSecondLayout() {
        super.configureSecondLayout();
        getSecondLayout().add(getSecondButtonLayout());
    }
}
