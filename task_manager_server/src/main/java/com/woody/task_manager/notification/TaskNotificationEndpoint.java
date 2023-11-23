package com.woody.task_manager.notification;

import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.PushMode;
import com.vaadin.flow.server.connect.Endpoint;
import com.vaadin.flow.server.connect.auth.AnonymousAllowed;
import com.vaadin.flow.shared.ui.Transport;

@Push(value = PushMode.MANUAL, transport = Transport.WEBSOCKET)
@Endpoint
@AnonymousAllowed
public class TaskNotificationEndpoint extends WebSocketEndpoint {

    public TaskNotificationEndpoint() {
        super("/ws/task-notifications");
    }

    // Дополнительная логика по обработке входящих сообщений, если необходимо
}
