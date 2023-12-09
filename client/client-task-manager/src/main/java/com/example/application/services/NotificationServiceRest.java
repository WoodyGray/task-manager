package com.example.application.services;

import jakarta.annotation.PostConstruct;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.security.GeneralSecurityException;
import java.security.Security;

@Service
public class NotificationServiceRest {
    private String bearerToken;
    private WebClient webClient;
    @Value("${application.server.url}")
    private String baseUrl;
    @Value("${vapid.public.key}")
    private String publicKey;


    public NotificationServiceRest(WebClient.Builder builder) {
//        Security.addProvider(new BouncyCastleProvider());
        webClient = builder.baseUrl(baseUrl).build();
    }

    public String getPublicKey() {
        return publicKey;
    }
    public void setBearerToken(String token){
        bearerToken = token;
    }

    public void subscribe(Subscription subscription){
        String response = webClient
                .post()
                .uri(baseUrl + "/notifications/subscribe")
                .header("Authorization",
                        "Bearer " + bearerToken)
                .body(Mono.just(subscription), Subscription.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(response);
    }
    public void unsubscribe(Subscription subscription){
        String response = webClient
                .post()
                .uri(baseUrl + "/notifications/unsubscribe")
                .body(Mono.just(subscription), Subscription.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(response);
    }
}
