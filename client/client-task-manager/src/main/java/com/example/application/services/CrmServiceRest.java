package com.example.application.services;


import com.example.application.data.User;
import com.example.application.services.dto.LogInDto;
import com.example.application.services.dto.SignUpDto;
import org.jsoup.helper.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service 
public class CrmServiceRest {

    private WebClient webClient;
    private String bearerToken;
    @Value("${application.server.url}")
    private String baseUrl;

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

    public String logIn(LogInDto logInDto){
        bearerToken = null;
        final String[] result = {"Log in success"};

        try {
            bearerToken = webClient
                    .post()
                    .uri(baseUrl + "/auth")
                    .body(Mono.just(logInDto), LogInDto.class)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

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