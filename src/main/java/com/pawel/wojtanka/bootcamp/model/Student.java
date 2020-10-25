package com.pawel.wojtanka.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Student {

    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String courseId;

}
