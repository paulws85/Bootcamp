package com.pawel.wojtanka.bootcamp.service;

import com.pawel.wojtanka.bootcamp.dto.CourseDto;
import com.pawel.wojtanka.bootcamp.factory.CourseDtoFactory;
import com.pawel.wojtanka.bootcamp.model.Course;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiService {

    private final CourseService courseService;
    private final CourseDtoFactory courseDtoFactory;

    public List<CourseDto> getDetailedCourses() {
        List<Course> courses = courseService.findAllCourses();

        return courses.stream()
            .map(courseDtoFactory::create)
            .collect(Collectors.toList());
    }

    public CourseDto getDetailedCourse(Long courseId) {
        Course course = courseService.findCourseByCourseId(courseId);

        return courseDtoFactory.create(course);
    }

}
