package com.example.application.views.authentication;

import com.example.application.components.ThemeButton;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route("")
@PageTitle("Authentication | Vaadin CRM")
public class AuthenticationView extends Div{
    HorizontalLayout buttonsLayout;
    VerticalLayout mainLayout;

    public AuthenticationView(){
        addClassName("authentication-background");
        addClassName("light-image-background");

        configureMainLayout();
        VerticalLayout verticalLayout = new VerticalLayout();
        Button themeButton = ThemeButton.configureThemeButtonWithImage(this);
        verticalLayout.add(themeButton, mainLayout);
        verticalLayout.setMaxWidth("300px");
        add(verticalLayout);
        getChildren().forEach(comp -> {
            comp.addClassName("light-background");
        });
    }

    private void configureButtonsLayout(){
        buttonsLayout = new HorizontalLayout();
        Button signUpButton = configureSignUpButton();
        Button logInButton = configureLogInButton();
        buttonsLayout.add(logInButton, signUpButton);
    }

    private Button configureSignUpButton(){
        Button button = new Button("Sign Up");
        button.addClickListener(e ->
                button.getUI().ifPresent(ui -> ui.navigate("/sign-up"))
        );
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        return button;
    }
    private Button configureLogInButton(){
        Button button = new Button("Log In");
        button.addClickListener(e ->
                button.getUI().ifPresent(ui -> ui.navigate("/log-in"))
                );
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        return button;
    }

    private void configureMainLayout(){
        mainLayout = new VerticalLayout();
        configureButtonsLayout();
        //<theme-editor-local-classname>
        mainLayout.addClassName("authentication-view-vertical-layout-1");
        mainLayout.add(new H2("Welcome to Task Manager"));
        mainLayout.add(buttonsLayout);
//        mainLayout.getElement().getThemeList().add("dark");
        mainLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        mainLayout.setSizeFull();
    }
}
