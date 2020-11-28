package com.pawel.wojtanka.bootcamp.repository;

import com.pawel.wojtanka.bootcamp.model.Course;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByTeacher_Id(Long teacherId);

    Optional<Course> findById(Long courseId);

    @Query(value = "SELECT c FROM Course c LEFT JOIN FETCH c.students")
    List<Course> deepFindCourse();

    @Query(value = "SELECT c FROM Course c LEFT JOIN FETCH c.students LEFT JOIN FETCH c.teacher WHERE c.id =: courseId")
    List<Course> deepFindCourse(Long courseId);

}
