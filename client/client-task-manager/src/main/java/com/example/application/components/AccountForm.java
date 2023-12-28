package com.example.application.components;

import com.example.application.data.User;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.avatar.AvatarVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class AccountForm extends VerticalLayout {
    public AccountForm(User user){
        Avatar xl = new Avatar(user.getUsername());
        xl.addThemeVariants(AvatarVariant.LUMO_XLARGE);
        add(xl);
        add(new H3("Full name: "+ user.getFullName()));
        add(new H3("Email: "+ user.getEmail()));
    }
}
