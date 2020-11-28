package com.pawel.wojtanka.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRest {

    private Long id;
    private Long postId;
    private String name;
    private String email;
    private String body;

}
