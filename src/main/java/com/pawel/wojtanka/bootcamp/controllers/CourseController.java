package com.pawel.wojtanka.bootcamp.controllers;

import com.pawel.wojtanka.bootcamp.model.Course;
import com.pawel.wojtanka.bootcamp.model.CourseMode;
import com.pawel.wojtanka.bootcamp.model.Student;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "kurs")
public class CourseController {

    private static final List<String> CITIES = Arrays.asList(null, "Warszawa", "Wrocław", "Poznań", "Kraków");
    private final List<Course> courses = new ArrayList<>();

//    @GetMapping(value = "/lista")
//    public String getCourseList(@RequestParam(name = "idKursu", defaultValue = "10",required = false) String courseId) {
//        System.out.println("Kurs o id: " + courseId);
//        return "/course/list";
//    }

    @GetMapping(value = "/lista/{idKursu}")
    public String getCourseList(@PathVariable(name = "idKursu") int courseId) {
        System.out.println("Kurs o id: " + courseId);
        return "/course/list";
    }

    @GetMapping(value = "/lista")
    public String getCourseList(@RequestParam(name = "idKursu", defaultValue = "10", required = false) String courseId,
                                Model model) {
        model.addAttribute("attributeId", courseId);
        return "/course/list";
    }

    @GetMapping("/dodaj")
    public String addCourse(Model model) {
        model.addAttribute("cities", CITIES);
        model.addAttribute("courseModes", CourseMode.values());
        model.addAttribute("course", Course.builder().build());
        return "/course/add-course";
    }

//    @GetMapping("/stworz")
//    public String addCourse(@RequestParam String name, @RequestParam String city) {
//        System.out.println("Course name: " + name + ", city: " + city);
//        return "/course/created-course";
//    }

    @PostMapping("/dodaj")
    public String addCourse(@ModelAttribute Course course, Model model) {
        model.addAttribute("cities", CITIES);
        model.addAttribute("courseModes", CourseMode.values());
        model.addAttribute("course", Course.builder().build());
        if (!course.getName().isEmpty() || !course.getCity().isEmpty() || course.getCourseMode() == CourseMode.NULL) {
            model.addAttribute("createdCourse", course);
        }
        course.setId(courses.size());
        System.out.println(course);
        courses.add(course);
        return "/course/add-course";
    }

    @GetMapping("/dodaj-studenta")
    public String addStudent(Model model) {
        model.addAttribute("student", Student.builder().build());
        model.addAttribute("courses", courses);
        return "/course/student-registration";
    }

    @PostMapping("/dodaj-studenta")
    public String addStudent(@ModelAttribute Student student, Model model) {
        System.out.println(student);
        model.addAttribute("student", Student.builder().build());
        model.addAttribute("courses", courses);

        return "/course/student-registration";
    }

}
