package com.example.application.views.authentication;

import com.example.application.components.ThemeButton;
import com.example.application.services.CrmServiceRest;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("sign-up")
@PageTitle("Sign-up | Vaadin CRM")
public class SignUpView extends Div {

    CrmServiceRest service;

    public SignUpView(CrmServiceRest service){
        this.service = service;
        addClassName("authentication-background");
        addClassName("light-image-background");

        SignUpForm signUpForm = new SignUpForm();
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setMaxWidth("550px");
        Button themeButton = ThemeButton.configureThemeButtonWithImage(this);
        verticalLayout.add(themeButton, signUpForm);
        add(verticalLayout);

        getChildren().forEach(comp -> {
            comp.addClassName("light-background");
        });

        SignUpFormBinder signUpFormBinder = new SignUpFormBinder(signUpForm, service);
        signUpFormBinder.addBinderAndValidator();
    }

}
