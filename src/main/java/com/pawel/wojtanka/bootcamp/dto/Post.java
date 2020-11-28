package com.pawel.wojtanka.bootcamp.dto;

import com.pawel.wojtanka.bootcamp.model.CommentRest;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private Long id;
    private Long userId;
    private String title;
    private String body;
    private List<CommentRest> comments;

}
