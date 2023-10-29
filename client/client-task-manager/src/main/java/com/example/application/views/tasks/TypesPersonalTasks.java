package com.example.application.views.tasks;

import com.example.application.data.PersonalSubtask;
import com.example.application.data.PersonalTask;
import com.example.application.data.Task;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.tabs.Tab;

import java.util.List;

public class TypesPersonalTasks extends TypesTasks{
    private List<PersonalTask> personalTaskList;

    public TypesPersonalTasks(List<PersonalTask> taskList) {
        super();
        personalTaskList = taskList;
    }

    @Override
    public void setContent(Tab tab) {
        getContent().removeAll();
        if (tab == null){
            return;
        }
        if (tab.equals(getInProcess())){
            getContent().add(new Paragraph("In process tasks"));
        }else if (tab.equals(getExtract())){
            getContent().add(new Paragraph("Extract tasks"));
        }else if (tab.equals(getNotStarted())){
            getContent().add(new Paragraph(personalTaskList.toString()));
        }
    }
}
