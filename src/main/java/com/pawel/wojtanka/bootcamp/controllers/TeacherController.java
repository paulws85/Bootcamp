package com.pawel.wojtanka.bootcamp.controllers;

import com.pawel.wojtanka.bootcamp.model.Course;
import com.pawel.wojtanka.bootcamp.model.Rule;
import com.pawel.wojtanka.bootcamp.model.Student;
import com.pawel.wojtanka.bootcamp.service.CourseService;
import com.pawel.wojtanka.bootcamp.service.RuleService;
import com.pawel.wojtanka.bootcamp.service.StudentService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/panel-trenera")
public class TeacherController {

    private final CourseService courseService;
    private final StudentService studentService;
    private final RuleService ruleService;

    @GetMapping("/")
    public String getTeacherDashboard() {
        return "teacher/teacher-panel";
    }

    @GetMapping("/dodaj-trenera")
    public String addTeacher(Model model) {
        model.addAttribute("teacher", Student.builder().build());
        model.addAttribute("courseList", courseService.findAllCourses());

        return "teacher/register-teacher";
    }

    @PostMapping("/dodaj-trenera/podsumowanie")
    public String addTeacher(@Valid @ModelAttribute("teacher") Student student, BindingResult bindingResult, @RequestParam Long[] courseIds, Model model) {
//        bindingResult.rejectValue("field_nsme", "errorCode", "defaultMessage");

        if (bindingResult.hasErrors()) {
//            bindingResult.getAllErrors().forEach(objectError -> {
//                System.out.println(objectError.getDefaultMessage());
//            });
            model.addAttribute("errorList", bindingResult.getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList()));
            model.addAttribute("teacher", student);
            model.addAttribute("courseList", courseService.findAllCourses());

            return "teacher/register-teacher";
        }

        List<Course> courses = courseService.findAllCoursesById(Arrays.asList(courseIds));
        student.setCourses(courses);
        Rule rule = ruleService.findRuleByName("teacher");
        student.setRule(rule);
        System.out.println("Student " + student);
        System.out.println("CoursesIds " + Arrays.asList(courseIds));
        studentService.saveStudent(student);

        return "teacher/summary";
    }

}
