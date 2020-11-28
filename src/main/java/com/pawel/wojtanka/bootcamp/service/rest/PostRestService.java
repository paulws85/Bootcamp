package com.pawel.wojtanka.bootcamp.service.rest;

import com.pawel.wojtanka.bootcamp.model.PostRest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PostRestService {

    public List<PostRest> getPosts() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<PostRest>> exchange = restTemplate.exchange(
            "https://jsonplaceholder.typicode.com/posts",
            HttpMethod.GET,
            HttpEntity.EMPTY,
            new ParameterizedTypeReference<List<PostRest>>() {
            }
        );
        return exchange.getBody();
    }

    public PostRest addPost(PostRest postRest) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

//        PostRest postRest = PostRest.builder()
//            .id(1L)
//            .userId(1L)
//            .title("Test title")
//            .body("Test body")
//            .build();

        HttpEntity<PostRest> httpEntity = new HttpEntity<>(postRest, httpHeaders);

        ResponseEntity<PostRest> exchange = restTemplate.exchange(
            "https://jsonplaceholder.typicode.com/posts",
            HttpMethod.POST,
            httpEntity,
            PostRest.class
        );

        System.out.println((exchange.getBody()));

        return exchange.getBody();
    }

}
