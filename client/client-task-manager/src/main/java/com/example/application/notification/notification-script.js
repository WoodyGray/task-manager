const socket = new SockJS('/ws');
const stompClient = Stomp.over(socket);
stompClient.connect({}, function(frame) {
    stompClient.subscribe('/user/topic/notification', function(notification) {
        // Обработка уведомления на стороне клиента
        Vaadin.Flow.notify({
            position: 'top-start',
            duration: 5000, // Длительность отображения в миллисекундах
            message: 'Ваше сообщение здесь!'
        });
    });
});