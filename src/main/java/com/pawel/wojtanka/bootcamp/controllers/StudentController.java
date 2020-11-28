package com.pawel.wojtanka.bootcamp.controllers;

import static java.util.Objects.isNull;

import com.pawel.wojtanka.bootcamp.model.Course;
import com.pawel.wojtanka.bootcamp.model.CourseMode;
import com.pawel.wojtanka.bootcamp.model.Student;
import com.pawel.wojtanka.bootcamp.service.CourseService;
import com.pawel.wojtanka.bootcamp.service.RuleService;
import com.pawel.wojtanka.bootcamp.service.StudentService;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/studenci")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final RuleService ruleService;
    private final List<Course> courseList = Arrays.asList(
        Course.builder()
            .id(1L)
            .name("SDA")
            .city("Warszawa")
            .courseMode(CourseMode.WEEKEND)
            .startDate(LocalDate.parse("2019-12-01"))
            .endDate(LocalDate.parse("2020-12-20"))
            .price(12000)
            .build(),
        Course.builder()
            .id(2L)
            .name("SDA2")
            .city("Warszawa")
            .courseMode(CourseMode.WEEKEND)
            .startDate(LocalDate.parse("2019-12-01"))
            .endDate(LocalDate.parse("2020-12-20"))
            .price(12000)
            .build());

    @GetMapping("/dodaj-studenta")
    public String addStudent(Model model) {
        model.addAttribute("student", Student.builder().build());
        model.addAttribute("courseList", courseService.findAllCourses());
        model.addAttribute("ruleList", ruleService.findAllRules());

        return "student/register-student";
    }

    @GetMapping("/dodaj-studenta/{courseId}")
    public String addStudent(@PathVariable Long courseId, Model model) {
        model.addAttribute("student", Student.builder().build());
        model.addAttribute("course", courseService.findCourseByCourseId(courseId));

        return "student/register-student";
    }

    @PostMapping("/dodaj-studenta/{courseId}/podsumowanie")
    public String addStudent(@PathVariable Long courseId, @ModelAttribute Student student) {
        String email = student.getEmail();
        Student existStudent = studentService.findBYEmail(email);
        if (isNull(existStudent)) {
            student.setRule(ruleService.findRuleByName("student"));
        } else {
            Course course = courseService.findCourseByCourseId(courseId);
            List<Course> existCourses = existStudent.getCourses();
            if (!existCourses.contains(course)) {
                existCourses.add(course);
            }
        }
        studentService.saveStudent(student);

        return "student/summary";
    }

}
