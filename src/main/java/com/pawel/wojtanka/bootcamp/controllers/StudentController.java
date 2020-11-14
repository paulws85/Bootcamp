package com.pawel.wojtanka.bootcamp.controllers;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/dodaj-studenta/podsumowanie")
    public String addStudent(@ModelAttribute Student student, @RequestParam Long[] courseIds) {
        student.setCourses(courseService.findAllCoursesById(Arrays.asList(courseIds)));
        student.setRule(ruleService.findRuleByName("student"));
        System.out.println(student);
        System.out.println(Arrays.asList(courseIds));
        studentService.saveStudent(student);

        return "student/summary";
    }

}
