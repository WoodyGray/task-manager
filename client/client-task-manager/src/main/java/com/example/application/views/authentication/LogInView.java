package com.example.application.views.authentication;

import com.example.application.components.ThemeButton;
import com.example.application.services.CrmServiceRest;

import com.example.application.services.dto.LogInDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;



@Route("log-in")
@PageTitle("Log-in | Vaadin CRM")
public class LogInView extends Div {

	CrmServiceRest service;
	LoginForm loginForm;

	public LogInView(CrmServiceRest service){
		this.service = service;
		addClassName("authentication-background");
		addClassName("light-image-background");
		addClassName("login-view");

		configureLoginForm();
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setMaxWidth("300px");
		Button themeButton = ThemeButton.configureThemeButtonWithImage(this);
		verticalLayout.add(themeButton, loginForm);

		add(verticalLayout);

		getChildren().forEach(comp -> {
			comp.addClassName("light-background");
		});
	}

	private void configureLoginForm(){
		loginForm = new LoginForm();
//		loginForm.setError(true);
		loginForm.addLoginListener(e -> {
			boolean isAuthenticated = authenticate(e);
			if (isAuthenticated){
				loginForm.getUI().ifPresent(ui -> ui.navigate("/tasks"));
			}else {
				loginForm.getUI().ifPresent(ui -> ui.getPage().reload());
			}
		});

//		loginForm.getElement().getThemeList().add("dark");
	}

	private Boolean authenticate(AbstractLogin.LoginEvent e){
		if (e.getUsername().isEmpty() || e.getPassword().isEmpty()){
			return false;
		}else {
			LogInDto dto = new LogInDto(
					e.getUsername(),
					e.getPassword()
			);
			String answer = service.logIn(dto);
			Notification.show(answer);
			if (!answer.equals("Log in success")){
				return false;
			}else{
				return true;
			}
		}
	}


}