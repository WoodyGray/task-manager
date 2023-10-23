package com.example.application.views.authentication;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("authentication")
@PageTitle("Authentication | Vaadin CRM")
public class AuthenticationView extends VerticalLayout {

    public AuthenticationView(){
        addClassName("authentication-background");


    }
}
