package com.pawel.wojtanka.bootcamp.factory;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import com.pawel.wojtanka.bootcamp.dto.CourseDto;
import com.pawel.wojtanka.bootcamp.dto.StudentDto;
import com.pawel.wojtanka.bootcamp.model.Course;
import com.pawel.wojtanka.bootcamp.model.Student;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseDtoFactory {

    private final StudentDtoFactory studentDtoFactory;

    public CourseDto create(Course course) {
        return CourseDto.builder()
            .id(course.getId())
            .name(course.getName())
            .city(course.getCity())
            .courseMode(course.getCourseMode())
            .startDate(course.getStartDate())
            .endDate(course.getEndDate())
            .price(course.getPrice())
            .teacher(getTeacher(course))
            .students(getCourseStudents(course.getStudents()))
            .build();
    }

    private StudentDto getTeacher(Course course) {
        return nonNull(course.getTeacher())
            ? studentDtoFactory.create(course.getTeacher())
            : null;
    }

    private List<StudentDto> getCourseStudents(final List<Student> students) {
        if (isNull(students)) {
            return Collections.emptyList();
        }

        return students.stream()
            .map(student -> StudentDto.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .phoneNumber(student.getPhoneNumber())
                .ratePerHour(student.getRatePerHour())
                .build())
            .collect(Collectors.toList());
    }

}
