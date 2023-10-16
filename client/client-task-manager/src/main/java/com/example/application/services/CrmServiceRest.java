package com.example.application.services;


import com.example.application.data.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service 
public class CrmServiceRest {

    private final RestTemplate restTemplate;
    private final String serverUrl;

    public CrmServiceRest(
            RestTemplate restTemplate, @Value("${application.server.url}") String serverUrl
    ) {
        this.restTemplate = restTemplate;
        this.serverUrl = serverUrl;
    }


    public List<User> getAllUsers() {

        System.out.println("Fetching all Comment objects through REST..");

        return restTemplate.exchange(
                serverUrl + "/users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                }
        ).getBody();
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