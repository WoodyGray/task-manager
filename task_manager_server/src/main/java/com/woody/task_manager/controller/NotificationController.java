package com.woody.task_manager.controller;

import com.woody.task_manager.dto.MessageDto;
import com.woody.task_manager.dto.NotificationDto;
import com.woody.task_manager.service.WebPushService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import nl.martijndwars.webpush.Subscription;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private WebPushService webPushService;

    @PostMapping("/subscribe")
    private ResponseEntity<String> subscribeToNotify(@RequestHeader (name = "Authorization") String token,
                                                     @RequestBody Subscription subscription){
        webPushService.subscribe(token.substring(7), subscription);
//        MessageDto messageDto = new MessageDto("Message from user", "boo");
//        webPushService.sendNotification(subscription, messageDto);
//
//        webPushService.subscribe(subscription);
//        webPushService.notifyAll("Message", "go home");
        return ResponseEntity.ok("subscribe success");
    }

    @PostMapping("/unsubscribe")
    private ResponseEntity<String> unsubscribeToNotify(
            @RequestHeader (name = "Authorization") String token,
            @RequestBody Subscription subscription){
        webPushService.unsubscribe(subscription);
//        MessageDto messageDto = new MessageDto("Message from user", "boo");
//        webPushService.sendNotification(subscription, messageDto);
//
//        webPushService.subscribe(subscription);
//        webPushService.notifyAll("Message", "go home");
        return ResponseEntity.ok("subscribe success");
    }
}
