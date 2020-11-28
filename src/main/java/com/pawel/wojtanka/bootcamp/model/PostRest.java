package com.pawel.wojtanka.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRest {

    private Long id;
    private Long userId;
    private String title;
    private String body;

}
