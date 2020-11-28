package com.pawel.wojtanka.bootcamp.controllers.api;

import com.pawel.wojtanka.bootcamp.model.UserRest;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestTemplateExampleController {

//    @GetMapping("/rest-template/get")
//    public UserRest[] getUsers() {
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<UserRest[]> exchange = restTemplate.exchange(
//            "https://jsonplaceholder.typicode.com/users",
//            HttpMethod.GET,
//            HttpEntity.EMPTY,
//            UserRest[].class
//        );
//
//        return exchange.getBody();
//    }

    @GetMapping("/rest-template/get")
    public List<UserRest> getUsers() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<UserRest>> exchange = restTemplate.exchange(
            "https://jsonplaceholder.typicode.com/users",
            HttpMethod.GET,
            HttpEntity.EMPTY,
            new ParameterizedTypeReference<List<UserRest>>() {
            }
        );

        return exchange.getBody();
    }

    @GetMapping("/rest-template/get/{id}")
    public UserRest getUsers(@PathVariable String id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserRest> exchange = restTemplate.exchange(
            "https://jsonplaceholder.typicode.com/users/" + id,
            HttpMethod.GET,
            HttpEntity.EMPTY,
            UserRest.class
        );

        return exchange.getBody();
    }

}
