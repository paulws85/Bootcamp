package com.pawel.wojtanka.bootcamp.service.rest;

import com.pawel.wojtanka.bootcamp.dto.Post;
import com.pawel.wojtanka.bootcamp.factory.PostFactory;
import com.pawel.wojtanka.bootcamp.model.CommentRest;
import com.pawel.wojtanka.bootcamp.model.PostRest;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRestService postRestService;
    private final CommentRestService commentRestService;
    private final PostFactory postFactory;

    public List<Post> getPosts() {
        List<PostRest> restPosts = postRestService.getPosts();
        List<CommentRest> commentRests = commentRestService.getComments();

        return restPosts.stream()
            .map(restPost -> postFactory.createPost(restPost, commentRests))
            .collect(Collectors.toList());
    }

}
