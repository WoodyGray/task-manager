package com.example.application.components;

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
    private List<VerticalLayout> layouts;
    private boolean isPublicTask;
    private int i;

    private HorizontalLayout nextAndBackButtonLayout;
    private Button nextButton;
    private Button backButton;


    public AddTaskDiv(CrmServiceRest service){
        this.service = service;
        configureNextButton();
        configureBackButton();
        configureNBButtonLayout();
        configureDivs();
        i = 0;
        add(layouts.get(i));
    }

    private void configureNBButtonLayout(){
        nextAndBackButtonLayout =
                new HorizontalLayout(
                        nextButton,
                        backButton
                );
    }

    private void configureNextButton(){
        nextButton = new Button("next");
        nextButton.addClickListener(click -> {
            if (i < layouts.size()){
                this.removeAll();
                i++;
                add(layouts.get(i));
            }
        });
    }

    private void configureBackButton(){
        backButton = new Button("back");
        backButton.addClickListener(click -> {
            if (i >= 0 && i <= layouts.size()){
                this.removeAll();
                i--;
                add(layouts.get(i));
            }
        });
    }

    private void configureDivs(){
        layouts = new ArrayList<>();
        layouts.add(createChoseTypeDiv());
        layouts.add(createDescriptionDiv());
        layouts.add(createDeadlineDiv());
//        divs.add(createSubtasksDiv());
//        if (isPublicTask) {
//            divs.add(createHostsAndUsersDiv());
//        }
    }

    private VerticalLayout createChoseTypeDiv(){
        VerticalLayout choseTypeDiv = new VerticalLayout();
        choseTypeDiv.add(new H2("Chose type of task"));
        ComboBox<String> typesBox = new ComboBox<>("types");
        typesBox.setItems("public task", "personal task");
        choseTypeDiv.add(typesBox);
        choseTypeDiv.add(nextButton);
        return choseTypeDiv;
    }

    private VerticalLayout createDescriptionDiv(){
        VerticalLayout descriptionDiv = new VerticalLayout();
        descriptionDiv.add(new H2("Write name and \ndescription of task"));

        TextArea taskNameArea = new TextArea();
        taskNameArea.setLabel("Task name");
        taskNameArea.setWidth("300px");
        taskNameArea.setRequiredIndicatorVisible(true);
        taskNameArea.setMinLength(1);
        taskNameArea.setMaxLength(100);
        taskNameArea.setValueChangeMode(ValueChangeMode.EAGER);
        taskNameArea.addValueChangeListener(e -> {
            e.getSource()
                    .setHelperText(e.getValue().length() + "/" + 100);
        });
        descriptionDiv.add(taskNameArea);

        TextArea descriptionArea = new TextArea();
        descriptionArea.setLabel("Description");
        descriptionArea.setWidth("300px");
        descriptionDiv.add(descriptionArea);

        descriptionDiv.add(nextAndBackButtonLayout);

        return descriptionDiv;
    }

    private VerticalLayout createDeadlineDiv(){
        VerticalLayout deadlineDiv = new VerticalLayout();

        deadlineDiv.add(new H2("Chose deadline of task"));

        DatePicker datePicker = new DatePicker("Deadline");
        LocalDate now = LocalDate.now(ZoneId.systemDefault());
        datePicker.setMin(now);
        datePicker.addValueChangeListener(event -> {
            LocalDate value = event.getValue();
            String errorMessage = null;
            if (value != null){
                if (value.compareTo(datePicker.getMin()) < 0){
                    errorMessage = "Too early, chose another date";
                }
            }
            datePicker.setErrorMessage(errorMessage);
        });
        deadlineDiv.add(datePicker);

        deadlineDiv.add(backButton);

        return deadlineDiv;
    }
}
