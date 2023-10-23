package com.example.application.views.authentication;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.Theme;

@Route("login") 
@PageTitle("Login | Vaadin CRM")
public class LoginView extends Div {

	private final LoginForm login = new LoginForm(); 

	public LoginView(){
		addClassName("login-view");
		addClassName("authentication-background");

		LoginForm loginForm = configureLoginForm();
		Button signUpButton = configureSignUpButton();

		add(loginForm, signUpButton);
	}

	private LoginForm configureLoginForm(){
		LoginForm loginForm = new LoginForm();
		loginForm.getElement().getThemeList().add("dark");

		return loginForm;
	}

	private Button configureSignUpButton(){
		Button signUpButton = new Button("Sign Up");
		return signUpButton;
	}

}