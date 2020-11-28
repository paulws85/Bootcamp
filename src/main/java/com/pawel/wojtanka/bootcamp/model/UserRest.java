package com.pawel.wojtanka.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRest {

    private Long id;
    private String name;
    private String username;
    private String email;

}
