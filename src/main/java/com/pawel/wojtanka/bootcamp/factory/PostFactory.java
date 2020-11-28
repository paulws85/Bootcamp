package com.pawel.wojtanka.bootcamp.factory;

import com.pawel.wojtanka.bootcamp.dto.Post;
import com.pawel.wojtanka.bootcamp.model.CommentRest;
import com.pawel.wojtanka.bootcamp.model.PostRest;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PostFactory {

    public Post createPost(PostRest postRest, List<CommentRest> restComments) {
        return Post.builder()
            .id(postRest.getId())
            .userId(postRest.getUserId())
            .title(postRest.getTitle())
            .body(postRest.getBody())
            .comments(extractComments(postRest.getId(), restComments))
            .build();
    }

    private List<CommentRest> extractComments(Long postId, List<CommentRest> restComments) {
        return restComments.stream()
            .filter(commentRest -> commentRest.getPostId().equals(postId))
            .collect(Collectors.toList());
    }


}
