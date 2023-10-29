package com.example.application.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.theme.lumo.Lumo;

public class ThemeButton {

    public static Button configureThemeButton(){

        Button button = new Button(createIcon(VaadinIcon.MOON));
        button.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        button.addClickListener(click -> {
            ThemeList themeList =
                    UI.getCurrent().getElement().getThemeList();

            if (themeList.contains(Lumo.DARK)){
                themeList.remove(Lumo.DARK);
                button.setIcon(createIcon(VaadinIcon.MOON));
            }else {
                themeList.add(Lumo.DARK);
                button.setIcon(createIcon(VaadinIcon.MOON_O));
            }
        });

        return button;
    }

    public static Button configureThemeButtonWithImage(Component component){

        Button button = new Button(createIcon(VaadinIcon.MOON));
        button.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        button.addClickListener(click -> {
            ThemeList themeList =
                    UI.getCurrent().getElement().getThemeList();

            if (themeList.contains(Lumo.DARK)){
                component.removeClassName("dark-image-background");
                component.addClassName("light-image-background");
                themeList.remove(Lumo.DARK);
                component.getChildren().forEach(comp -> {
                    comp.removeClassName("dark-background");
                    comp.addClassName("light-background");
                });
                button.setIcon(createIcon(VaadinIcon.MOON));

            }else {
                component.removeClassName("light-image-background");
                component.addClassName("dark-image-background");
                themeList.add(Lumo.DARK);
                component.getChildren().forEach(comp -> {
                    comp.removeClassName("light-background");
                    comp.addClassName("dark-background");
                });
                button.setIcon(createIcon(VaadinIcon.MOON_O));
            }
        });

        return button;
    }

    private static Icon createIcon(VaadinIcon vaadinIcon) {
        Icon icon = vaadinIcon.create();
        icon.getStyle().set("padding", "var(--lumo-space-xs");
        return icon;
    }
}
