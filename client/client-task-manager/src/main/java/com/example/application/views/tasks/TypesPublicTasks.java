package com.example.application.views.tasks;

import com.example.application.data.PublicTask;
import com.example.application.data.Task;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.tabs.Tab;

import java.util.List;

public class TypesPublicTasks extends TypesTasks{
    private List<PublicTask> publicTaskList;

    public TypesPublicTasks(List<PublicTask> taskList) {
        super();
        publicTaskList = taskList;
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
            getContent().add(new Paragraph(publicTaskList.toString()));
        }
    }
}
