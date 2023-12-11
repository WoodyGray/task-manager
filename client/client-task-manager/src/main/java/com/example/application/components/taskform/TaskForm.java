package com.example.application.components.taskform;

import com.example.application.services.CrmServiceRest;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class TaskForm extends VerticalLayout {
    private CrmServiceRest service;

    public TaskForm(CrmServiceRest service){
        this.service = service;
        
    }
}
