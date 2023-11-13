package com.example.application.views.tasks.personalTasks;

import com.example.application.data.PersonalTask;
import com.example.application.data.Task;
import com.example.application.views.tasks.TasksAccordion;
import com.example.application.views.tasks.TypesTasks;
import com.vaadin.flow.component.tabs.Tab;

import java.util.ArrayList;
import java.util.List;

public class TypesPersonalTasks extends TypesTasks {
    private final List<PersonalTask> personalTaskList;
    private final List<PersonalTask> extractTasks;
    private final List<PersonalTask> inProcessTasks;
    private final List<PersonalTask> notStartedTasks;

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
            getContent().add(new PersonalTasksAccordion(inProcessTasks));
        }else if (tab.equals(getExtract())){
            getContent().add(new PersonalTasksAccordion(extractTasks));
        }else if (tab.equals(getNotStarted())){
            getContent().add(new PersonalTasksAccordion(notStartedTasks));
        }
    }

    private List<PersonalTask> getSomeStatusTasks(int status){
        List<PersonalTask> result = null;
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
