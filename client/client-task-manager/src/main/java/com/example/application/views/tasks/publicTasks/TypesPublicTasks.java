package com.example.application.views.tasks.publicTasks;

import com.example.application.data.PublicTask;
import com.example.application.data.Task;
import com.example.application.views.tasks.TasksAccordion;
import com.example.application.views.tasks.TypesTasks;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.tabs.Tab;

import java.util.ArrayList;
import java.util.List;

public class TypesPublicTasks extends TypesTasks {
    private final List<PublicTask> publicTaskList;
    private final List<PublicTask> extractTasks;
    private final List<PublicTask> inProcessTasks;
    private final List<PublicTask> notStartedTasks;
    private String username;

    public TypesPublicTasks(List<PublicTask> taskList, String username) {
        super();
        this.username = username;
        publicTaskList = taskList;
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
            getContent().add(new PublicTasksAccordion(inProcessTasks, username));
        }else if (tab.equals(getExtract())){
            getContent().add(new PublicTasksAccordion(extractTasks, username));
        }else if (tab.equals(getNotStarted())){
            getContent().add(new PublicTasksAccordion(notStartedTasks, username));
        }
    }

    private List<PublicTask> getSomeStatusTasks(int status){
        List<PublicTask> result = null;
        if (publicTaskList != null){
            result = new ArrayList<>();
            for (PublicTask task: publicTaskList
                 ) {
                if (task.getStatus() == status)
                    result.add(task);
            }
        }
        return result;
    }

}
