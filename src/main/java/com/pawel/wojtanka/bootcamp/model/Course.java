package com.pawel.wojtanka.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Course {

    private Integer id;
    private String name;
    private String city;
    private CourseMode courseMode;

}
