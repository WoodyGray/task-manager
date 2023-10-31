package com.example.application.services;


import com.example.application.data.PersonalTask;
import com.example.application.data.PublicTask;
import com.example.application.data.User;
import com.example.application.services.dto.LogInDto;
import com.example.application.services.dto.SignUpDto;
import com.example.application.services.dto.TokenResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vaadin.flow.component.notification.Notification;
import org.jsoup.helper.ValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service 
public class CrmServiceRest {

    private WebClient webClient;
    @JsonProperty("token")
    private TokenResponseDto bearerToken;
    @Value("${application.server.url}")
    private String baseUrl;
    private User user;

    public CrmServiceRest(WebClient.Builder builder) {

        webClient = builder.baseUrl(baseUrl).build();
    }


    public List<User> getAllUsers() {

        System.out.println("Fetching all Comment objects through REST..");


////        return restTemplate.exchange(
////                serverUrl + "/users",
////                HttpMethod.GET,
////                null,
////                new ParameterizedTypeReference<List<User>>() {
////                }
//        ).getBody();
        return new ArrayList<>();
    }

    public List<PublicTask> getPublicTasks(){
        if (bearerToken != null){
            try {
                Flux<PublicTask> publicTaskFlux =
                        webClient.get()
                                .uri(baseUrl + "/personal-info/public-tasks")
                                .header("Authorization",
                                        "Bearer " + bearerToken.getBearerToken())
                                .retrieve()
                                .bodyToFlux(PublicTask.class);
                System.out.println(bearerToken);
                List<PublicTask> result = publicTaskFlux
                        .collect(Collectors.toList())
                        .share().block();

                if (result == null){
                    return new ArrayList<>();
                }else {
                    return result;
                }

            }catch (WebClientResponseException e){
                Notification.show("Reauthorize");
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<PersonalTask> getPersonalTasks(){
        if (bearerToken != null){
            try {
                WebClient.RequestHeadersSpec<?> spec
                        = webClient
                        .get()
                        .uri(baseUrl + "/personal-info/personal-tasks")
                        .header("Authorization",
                                "Bearer " +bearerToken.getBearerToken());

                return spec.retrieve()
                        .toEntityList(PersonalTask.class).block().getBody();



            }catch (WebClientResponseException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public String logIn(LogInDto logInDto){
        bearerToken = null;
        final String[] result = {"Log in success"};

        try {
            bearerToken = webClient
                    .post()
                    .uri(baseUrl + "/auth")
                    .body(Mono.just(logInDto), LogInDto.class)
                    .retrieve()
                    .bodyToMono(TokenResponseDto.class)
                    .block();

//            Mono<String> tokenMono = webClient.post()
//                    .uri(baseUrl + "/auth")
//                    .body(Mono.just(logInDto), LogInDto.class)
//                    .retrieve()
//                    .bodyToMono(String.class);
//
//            bearerToken = tokenMono
//                    .share().block();

        }catch (WebClientResponseException e){
            result[0] = e.getStatusText();
        }
        return result[0];
    }

    public void signUp(SignUpDto signUpDto) throws ValidationException{
        if(signUpDto.getUsername() !=null
        && signUpDto.getFullName() != null
        && signUpDto.getEmail() != null
        && signUpDto.getPassword() != null
        && signUpDto.getConfirmPassword() != null
        && signUpDto.getPassword().equals(signUpDto.getConfirmPassword())){
            try {
                webClient
                        .post()
                        .uri(baseUrl + "/registration")
                        .body(Mono.just(signUpDto), SignUpDto.class)
                        .retrieve()
                        .bodyToMono(Void.class)
                        .block();
            }catch (WebClientResponseException e){
                throw new ValidationException(e.getStatusText());
            }
        }else {
            throw new ValidationException("Check the fields on correct");
        }

    }

}