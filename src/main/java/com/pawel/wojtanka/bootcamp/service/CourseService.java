package com.pawel.wojtanka.bootcamp.service;

import com.pawel.wojtanka.bootcamp.model.Course;
import com.pawel.wojtanka.bootcamp.model.Student;
import com.pawel.wojtanka.bootcamp.repository.CourseRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    public List<Course> findAllCourses() {
        List<Course> courses =  courseRepository.deepFindCourse();

        courses.forEach(course -> {
            List<Student> teachers = course.getStudents().stream()
                .filter(student -> student.getRule().getRuleName().equals("teacher"))
                .collect(Collectors.toList());

            course.setStudents(teachers);
        });

        return courses;
    }

    public List<Course> findAllCoursesById(List<Long> courseIds) {
        return courseRepository.findAllById(courseIds);
    }

    public Course findCourseByCourseId(Long courseID) {
        return courseRepository.findById(courseID)
            .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono kursu o id: " + courseID));
    }

}
