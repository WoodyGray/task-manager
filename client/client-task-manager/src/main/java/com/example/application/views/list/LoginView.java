package com.example.application.views.list;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.Theme;

@Route("login") 
@PageTitle("Login | Vaadin CRM")
@AnonymousAllowed
public class LoginView extends Div {

	private final LoginForm login = new LoginForm(); 

	public LoginView(){
		addClassName("login-view");
		LoginForm loginForm = new LoginForm();
		loginForm.getElement().getThemeList().add("dark");
		add(loginForm);
	}

//	@Override
//	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
//		// inform the user about an authentication error
//		if(beforeEnterEvent.getLocation()
//        .getQueryParameters()
//        .getParameters()
//        .containsKey("error")) {
//            login.setError(true);
//        }
//	}
}