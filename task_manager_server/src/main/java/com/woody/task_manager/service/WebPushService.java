package com.woody.task_manager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.woody.task_manager.dto.MessageDto;
import com.woody.task_manager.entity.SubscriptionEntity;
import com.woody.task_manager.entity.User;
import com.woody.task_manager.repository.SubscriptionRepository;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;

import org.apache.http.HttpResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.Security;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class WebPushService {

    @Value("${vapid.public.key}")
    private String publicKey;
    @Value("${vapid.private.key}")
    private String privateKey;
    @Value("${vapid.subject}")
    private String subject;

    private PushService pushService;

    @Autowired
    private UserService userService;
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    public void setSubscriptionRepository(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @PostConstruct
    private void init() throws GeneralSecurityException {
        Security.addProvider(new BouncyCastleProvider());
        pushService = new PushService(publicKey, privateKey, subject);
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void sendNotification(Subscription subscription, String messageJson) {
        try {
            HttpResponse response = pushService.send(new Notification(subscription, messageJson));
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 201) {
                System.out.println("Server error, status code:" + statusCode);
                InputStream content = response.getEntity().getContent();
//                List<String> strings = IOUtils.readLines(content, "UTF-8");
//                System.out.println(strings);
            }
        } catch (GeneralSecurityException | IOException | JoseException | ExecutionException
                 | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendNotification(Subscription subscription, MessageDto messageDto) {
        try {
            String messageJson = mapper.writeValueAsString(messageDto);
            sendNotification(subscription, messageJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    public void sendNotification(SubscriptionEntity entity, MessageDto messageDto){
        sendNotification(
                convertToSubscription(entity),
                messageDto
        );
    }

    public void subscribe(String token, Subscription subscription) {
//        System.out.println("Subscribed to " + subscription.endpoint);
//        /*
//         * Note, in a real world app you'll want to persist these
//         * in the backend. Also, you probably want to know which
//         * subscription belongs to which user to send custom messages
//         * for different users. In this demo, we'll just use
//         * endpoint URL as key to store subscriptions in memory.
//         */
//        endpointToSubscription.put(subscription.endpoint, subscription);

        SubscriptionEntity entity = new SubscriptionEntity();
        entity.setAuth(subscription.keys.auth);
        entity.setP256dh(subscription.keys.p256dh);
        entity.setEndpoint(subscription.endpoint);
        entity.setUser(userService.findByToken(token));
        subscriptionRepository.save(entity);
    }

    public void unsubscribe(Subscription subscription) {
//        System.out.println("Unsubscribed " + subscription.endpoint + " auth:" + subscription.keys.auth);
//        endpointToSubscription.remove(subscription.endpoint);
        subscriptionRepository.deleteById(subscription.endpoint);
    }

    ObjectMapper mapper = new ObjectMapper();

    public void notifyAll(String title, String body) {
        try {
            String msg = mapper.writeValueAsString(new MessageDto(title, body));
            subscriptionRepository.findAll().forEach(subscriptionEntity -> {
                sendNotification(convertToSubscription(subscriptionEntity), msg);
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Subscription convertToSubscription(SubscriptionEntity entity){
        return new Subscription(entity.getEndpoint(),
                new Subscription.Keys(entity.getP256dh(), entity.getAuth()));
    }


}
