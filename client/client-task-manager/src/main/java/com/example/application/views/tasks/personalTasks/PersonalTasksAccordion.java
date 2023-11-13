package com.example.application.views.tasks.personalTasks;

import com.example.application.data.PersonalTask;
import com.example.application.data.Task;
import com.example.application.views.tasks.TasksAccordion;

import java.util.List;

public class PersonalTasksAccordion extends TasksAccordion {
    public PersonalTasksAccordion(List<PersonalTask> tasks) {
        super();
        addTasks(tasks, PersonalTaskElement.class);
    }
}
