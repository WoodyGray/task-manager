package com.example.application.views;


import com.example.application.notification.NotificationListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.communication.PushMode;
import com.vaadin.flow.shared.ui.Transport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Route("client")
@Push(value = PushMode.MANUAL, transport = Transport.WEBSOCKET)
public class MainView extends VerticalLayout {

    private final NotificationListener notificationListener;

    @Autowired
    public MainView(NotificationListener notificationListener) {
        this.notificationListener = notificationListener;

//        Button notifyButton = new Button("Send Notification", event -> sendNotification());
//        add(notifyButton);

        Notification.show("Welcome to Vaadin with WebSocket!");
    }

//    private void sendNotification() {
//        // Ваш код для отправки уведомления на сервер
//    }

    // Этот метод будет вызван автоматически при получении уведомления
    public void updateNotification(String newNotification) {
        UI.getCurrent().access(() -> Notification.show(newNotification));
    }
}

