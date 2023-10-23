package com.example.application.services;


import com.example.application.data.User;
import com.example.application.services.dto.LogInDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
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

    public String getBearerToken(LogInDto logInDto){
        bearerToken = null;
        try {
            bearerToken = webClient
                    .post()
                    .uri(baseUrl+"/auth")
                    .body(Mono.just(logInDto), LogInDto.class)
                    .retrieve()
                    .bodyToMono(String.class).block();
        }catch (WebException e){

        }

        if (bearerToken != null){
            return true;
        }
        return false;
    }
//
//    public long countContacts() {
//        return contactRepository.count();
//    }
//
//    public void deleteContact(Contact contact) {
//        contactRepository.delete(contact);
//    }
//
//    public void saveContact(Contact contact) {
//        if (contact == null) {
//            System.err.println("Contact is null. Are you sure you have connected your form to the application?");
//            return;
//        }
//        contactRepository.save(contact);
//    }
//
//    public List<Company> findAllCompanies() {
//        return companyRepository.findAll();
//    }
//
//    public List<Status> findAllStatuses(){
//        return statusRepository.findAll();
//    }
}