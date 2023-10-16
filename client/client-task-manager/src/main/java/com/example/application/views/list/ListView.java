package com.example.application.views.list;

import com.example.application.data.User;
import com.example.application.services.CrmServiceRest;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "")
@PageTitle("Contacts | Vaadin CRM")
public class ListView extends VerticalLayout {
    Grid<User> grid = new Grid<>(User.class);
    TextField filterText = new TextField();
    CrmServiceRest service;

    public ListView(CrmServiceRest crmServiceRest) {
        service = crmServiceRest;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        add(getToolbar(), grid);
        updateList();
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("fullName", "login", "password");
//        grid.addColumn(showPersonalTaskButton).setHeader("Status");
//        grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");
        grid.addComponentColumn(user -> {
            Button showPersonalTaskButton = new Button("Personal tasks");
            return showPersonalTaskButton;
        });
        grid.addComponentColumn(user -> {
            Button showPublicTaskButton = new Button("Public tasks");
            showPublicTaskButton.addClickListener(buttonClickEvent ->
                showPublicTaskButton.getUI().ifPresent(ui -> ui.navigate("/1"))
            );
//            showPublicTaskButton.addClickListener()
            return showPublicTaskButton;
        });
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addContactButton = new Button("Add contact");

        var toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void updateList() {
        grid.setItems(service.getAllUsers());
    }
}