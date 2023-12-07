// import SockJS from 'sockjs-client';
// import * as Stomp from 'stompjs';
//
// // Ваш код для создания и подключения к WebSocket
// const socket = new SockJS('/ws');
// const privateStompClient = Stomp.over(socket);
// privateStompClient.connect({}, function (frame) {
//     console.log(frame)
//     privateStompClient.subscribe('/user/specific', function (result) {
//         console.log(result.body)
//         Notification.show(JSON.parse(result.body))
//     })
//     file
// })
let title = "JavaScript Jeep";
let icon = 'https://homepages.cae.wisc.edu/~ece533/images/airplane.png';
let body = "Message to be displayed";
var notification = new Notification('Title', { body, icon });
