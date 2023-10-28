package com.example.application.views.authentication;

import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Data;

import java.util.stream.Stream;


@Data
public class SignUpForm extends FormLayout {

    private H2 title;
    private TextField fullName;
    private TextField username;
    private TextField email;
    private PasswordField password;
    private PasswordField confirmPassword;
    private Span errorMessageField;
    private Button signUpButton;

    public SignUpForm(){
        addClassName("signup-view");

        title = new H2("Registration form");
        fullName = new TextField("Full name");
        username = new TextField("Username");
        email = new TextField("Email");
        password = new PasswordField("Password");
        confirmPassword = new PasswordField("Confirm password");
        errorMessageField = new Span();

        setRequireIndicatorVisible(fullName, username, email,
                password, confirmPassword);

        signUpButton = new Button("Sign up");
        signUpButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        add(title, fullName, username, email,
                password, confirmPassword,
                errorMessageField, signUpButton);


        setResponsiveSteps(
                new ResponsiveStep("0", 1),
                new ResponsiveStep("500px", 2)
        );

        setColspan(fullName, 2);
        setColspan(errorMessageField, 2);
        setColspan(signUpButton, 2);

//        getElement().getThemeList().add("dark");
    }

    private void setRequireIndicatorVisible(HasValueAndElement<?, ?>... components){
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }
}
