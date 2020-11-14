package com.pawel.wojtanka.bootcamp.service;

import com.pawel.wojtanka.bootcamp.model.Course;
import com.pawel.wojtanka.bootcamp.model.Student;
import com.pawel.wojtanka.bootcamp.repository.CourseRepository;
import com.pawel.wojtanka.bootcamp.repository.StudentRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.plaf.IconUIResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> findUserByRoleName(String roleName) {
        return studentRepository.findByRule_RuleNameContains(roleName);
    }

    public List<Student> findAllUsers() {
        return studentRepository.findAll();
    }

    @Transactional
    public void deleteUser(Long userId) {
        List<Course> courseList = courseRepository.findByTeacher_Id(userId);
        courseList.forEach(course -> {
            course.setTeacher(null);
            course.setStudents(course.getStudents().stream()
                .filter(student -> !student.getId().equals(userId))
                .collect(Collectors.toList()));
        });
        courseRepository.saveAll(courseList);
        studentRepository.deleteById(userId);
    }

    public void editUser(Student user) {

    }

    public Student findUser(Long userId) {
        return studentRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Typed user ID can't be found: " + userId));
    }

}
