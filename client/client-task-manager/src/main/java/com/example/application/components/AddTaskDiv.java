package com.example.application.components;

import com.example.application.components.taskform.PersonalTaskForm;
import com.example.application.components.taskform.PublicTaskForm;
import com.example.application.services.CrmServiceRest;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.value.ValueChangeMode;
import lombok.Data;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Data
public class AddTaskDiv extends Div {
    private CrmServiceRest service;
    private ComboBox<String> typesBox;

    private VerticalLayout choseTypeDiv;
    private VerticalLayout publicTaskForm;
    private VerticalLayout personalTaskForm;

    private Button nextButton;
    private Button backButton;


    public AddTaskDiv(CrmServiceRest service){
        this.service = service;
        configureNextButton();
        configureBackButton();
        configurePublicTaskForm();
        configurePersonalTaskForm();
        choseTypeDiv = createChoseTypeDiv();
        add(choseTypeDiv);
    }


    private void configureNextButton(){
        nextButton = new Button("next");
        nextButton.addClickListener(click -> {
            if (!typesBox.isEmpty()) {
                this.removeAll();
                if (typesBox.getValue().equals("public task")) {
                    publicTaskForm.add(backButton);
                    add(publicTaskForm);
                } else if (typesBox.getValue().equals("personal task")) {
                    personalTaskForm.add(backButton);
                    add(personalTaskForm);
                }
            }
        });
    }

    private void configureBackButton(){
        backButton = new Button("back");
        backButton.addClickListener(click -> {
            this.removeAll();
            add(choseTypeDiv);
        });
    }

    private void configurePublicTaskForm(){
        publicTaskForm = new PublicTaskForm(service);
    }
    private void configurePersonalTaskForm(){
        personalTaskForm = new PersonalTaskForm(service);
    }


    private VerticalLayout createChoseTypeDiv(){
        VerticalLayout choseTypeDiv = new VerticalLayout();
        choseTypeDiv.add(new H2("Chose type of task"));
        typesBox = new ComboBox<>("types");
        typesBox.setItems("public task", "personal task");
        choseTypeDiv.add(typesBox);
        choseTypeDiv.add(nextButton);
        return choseTypeDiv;
    }

}
