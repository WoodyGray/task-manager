package com.example.application.views;

import com.example.application.notification.WebPushService;
import com.example.application.notification.WebPushToggle;
import com.example.application.services.NotificationServiceRest;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Value;

@PageTitle("Web Push")
@Route(value = "notify")
public class PushView extends VerticalLayout {

    public PushView(NotificationServiceRest notificationServiceRest) {

        var toggle = new WebPushToggle(notificationServiceRest.getPublicKey());
        var messageInput = new TextField("Message:");
        var sendButton = new Button("Notify all users!");
        var messageLayout = new HorizontalLayout(messageInput, sendButton);
        messageLayout.setDefaultVerticalComponentAlignment(Alignment.BASELINE);

        add(
            new H1("Web Push Notification Demo"),
            toggle,
            messageLayout
        );

        toggle.addSubscribeListener(e -> {
            notificationServiceRest.subscribe(e.getSubscription());
        });
        toggle.addUnsubscribeListener(e -> {
            notificationServiceRest.unsubscribe(e.getSubscription());
        });

//        sendButton.addClickListener(e -> webPushService.notifyAll("Message from user", messageInput.getValue()));
    }
}
