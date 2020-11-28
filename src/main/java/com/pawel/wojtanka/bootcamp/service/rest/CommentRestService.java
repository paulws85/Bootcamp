package com.pawel.wojtanka.bootcamp.service.rest;

import com.pawel.wojtanka.bootcamp.model.CommentRest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CommentRestService {

    public List<CommentRest> getComments() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<CommentRest>> exchange = restTemplate.exchange(
            "https://jsonplaceholder.typicode.com/comments",
            HttpMethod.GET,
            HttpEntity.EMPTY,
            new ParameterizedTypeReference<List<CommentRest>>() {
            }
        );
        return exchange.getBody();
    }

}
