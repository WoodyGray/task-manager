package com.example.application.views.tasks;

import com.example.application.data.PersonalTask;
import com.example.application.data.PublicTask;
import com.example.application.data.Task;
import com.vaadin.flow.component.tabs.Tab;

import java.util.ArrayList;
import java.util.List;

public class TypesPersonalTasks extends TypesTasks{
    private final List<PersonalTask> personalTaskList;
    private final List<Task> extractTasks;
    private final List<Task> inProcessTasks;
    private final List<Task> notStartedTasks;

    public TypesPersonalTasks(List<PersonalTask> taskList) {
        super();
        personalTaskList = taskList;
        extractTasks = getSomeStatusTasks(2);
        setBadge(getExtractBadge(),
                extractTasks.size());
        inProcessTasks = getSomeStatusTasks(1);
        setBadge(getInProcessBadge(),
                inProcessTasks.size());
        notStartedTasks = getSomeStatusTasks(0);
        setBadge(getNotStartedBadge(),
                notStartedTasks.size());

    }

    @Override
    public void setContent(Tab tab) {
        getContent().removeAll();
        if (tab == null){
            return;
        }
        if (tab.equals(getInProcess())){
            getContent().add(new TasksAccordion(inProcessTasks));
        }else if (tab.equals(getExtract())){
            getContent().add(new TasksAccordion(extractTasks));
        }else if (tab.equals(getNotStarted())){
            getContent().add(new TasksAccordion(notStartedTasks));
        }
    }

    private List<Task> getSomeStatusTasks(int status){
        List<Task> result = null;
        if (personalTaskList != null){
            result = new ArrayList<>();
            for (PersonalTask task: personalTaskList
            ) {
                if (task.getStatus() == status)
                    result.add(task);
            }
        }
        return result;
    }
}
