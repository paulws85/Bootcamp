package com.pawel.wojtanka.bootcamp.dto;

import com.pawel.wojtanka.bootcamp.model.CourseMode;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

    private Long id;
    private String name;
    private String city;
    private CourseMode courseMode;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer price;
    private List<StudentDto> students;
    private StudentDto teacher;

}
