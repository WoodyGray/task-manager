package com.example.application.views.list;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route("login-content")
public class LoginRichContent extends Div {

    public LoginRichContent() {
        // See login-rich-content.css
        addClassName("login-rich-content");

        LoginForm loginForm = new LoginForm();
        loginForm.getElement().getThemeList().add("dark");

//        Image image = new Image("images/earth.jpg", "My Alt Image");
//        image.setSizeFull();
//        set

        add(loginForm);
    }

}