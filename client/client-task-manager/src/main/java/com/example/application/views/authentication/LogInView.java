package com.example.application.views.authentication;

import com.example.application.services.CrmServiceRest;
import com.example.application.services.dto.LogInDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.Theme;



@Route("log-in")
@PageTitle("Log-in | Vaadin CRM")
public class LogInView extends Div {

	CrmServiceRest service;

	public LogInView(CrmServiceRest service){
		this.service = service;
		addClassName("authentication-background");
		addClassName("login-view");

		LoginForm loginForm = new LoginForm();
		loginForm.setError(true);
		loginForm.addLoginListener(e -> {
			if (e.getUsername().isEmpty() || e.getPassword().isEmpty()){
			}else {
				LogInDto dto = new LogInDto(
						e.getUsername(),
						e.getPassword()
				);
				String answer = service.getBearerToken(dto);
				Notification.show(answer);
			}
		});

		loginForm.getElement().getThemeList().add("dark");

		add(loginForm);
	}


}