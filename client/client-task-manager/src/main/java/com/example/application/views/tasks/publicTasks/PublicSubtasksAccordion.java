package com.example.application.views.tasks.publicTasks;

import com.example.application.data.PublicSubtask;
import com.example.application.views.tasks.TasksAccordion;

import java.util.List;

public class PublicSubtasksAccordion extends TasksAccordion {
    public PublicSubtasksAccordion(List<PublicSubtask> subtasks) {
        super();
        if (subtasks != null)
            addTasks(subtasks, PublicSubtaskElement.class);
    }
}
