package com.example.application.views.authentication;

import com.example.application.services.CrmServiceRest;
import com.example.application.services.dto.SignUpDto;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;


public class SignUpFormBinder {
    private SignUpForm signUpForm;
    private CrmServiceRest serviceRest;
    private boolean enablePasswordValidator;

    public SignUpFormBinder(SignUpForm form, CrmServiceRest serviceRest){
        signUpForm = form;
        this.serviceRest = serviceRest;
    }

    public void addBinderAndValidator(){
        BeanValidationBinder<SignUpDto> binder =
                new BeanValidationBinder<>(SignUpDto.class);
        binder.bindInstanceFields(signUpForm);

        binder.forField(signUpForm.getPassword())
                .withValidator(this::passwordValidator).bind("password");

        signUpForm.getConfirmPassword().addValueChangeListener(e -> {
            enablePasswordValidator = true;
            binder.validate();
        });

        binder.setStatusLabel(signUpForm.getErrorMessageField());

        signUpForm.getSignUpButton().addClickListener(e -> {
            try {
                SignUpDto userDetails = new SignUpDto();

                binder.writeBean(userDetails);

                serviceRest.signUp(userDetails);

                showSuccess(userDetails);

                signUpForm.getSignUpButton().getUI().ifPresent(ui -> ui.navigate("/log-in"));
            } catch (ValidationException validationException) {
                showError(validationException.getMessage());
                validationException.printStackTrace();
            }
        });
    }

    private ValidationResult passwordValidator(String pass, ValueContext ctx){

        if (pass == null || pass.length() < 8){
            return ValidationResult.error("Password should be at least 8 characters long");
        }

        if(!enablePasswordValidator){
            enablePasswordValidator = true;

            return ValidationResult.ok();
        }

        String pass2 = signUpForm.getConfirmPassword().getValue();
        if (pass != null && pass.equals(pass2)){
            return ValidationResult.ok();
        }

        return ValidationResult.error("Passwords do not match");
    }

    private void showSuccess(SignUpDto signUpDto){
        Notification notification =
                Notification.show("Data saved, welcome" + signUpDto.getUsername()
                + ", now u can Log in!");
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }

    private void showError(String message){
        Notification notification =
                Notification.show(message);
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
    }
}
