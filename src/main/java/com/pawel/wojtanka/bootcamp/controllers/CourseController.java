package com.pawel.wojtanka.bootcamp.controllers;

import com.pawel.wojtanka.bootcamp.model.Course;
import com.pawel.wojtanka.bootcamp.model.CourseMode;
import com.pawel.wojtanka.bootcamp.service.CourseService;
import com.pawel.wojtanka.bootcamp.service.StudentService;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "kursy")
@RequiredArgsConstructor
public class CourseController {

    private static final List<String> CITIES = Arrays.asList(null, "Warszawa", "Wrocław", "Poznań", "Kraków");
    private final CourseService courseService;
    private final StudentService studentService;

//    @GetMapping(value = "/lista")
//    public String getCourseList(@RequestParam(name = "idKursu", defaultValue = "10",required = false) String courseId) {
//        System.out.println("Kurs o id: " + courseId);
//        return "course/list";
//    }

    @GetMapping(value = "/lista/{idKursu}")
    public String getCourseList(@PathVariable(name = "idKursu") int courseId) {
        System.out.println("Kurs o id: " + courseId);
        return "course/list";
    }

    @GetMapping(value = "/lista")
    public String getCourseList(@RequestParam(name = "idKursu", defaultValue = "10", required = false) String courseId,
                                Model model) {
        model.addAttribute("attributeId", courseId);
        model.addAttribute("courseList", courseService.findAllCourses());

        return "course/list";
    }

    @GetMapping("/dodaj-kurs")
    public String addCourse(Model model) {
        model.addAttribute("cities", CITIES);
        model.addAttribute("courseModeList", CourseMode.values());
        model.addAttribute("course", Course.builder().build());
        model.addAttribute("teacherList", studentService.findUserByRoleName("teacher"));

        return "course/add-course";
    }

//    @GetMapping("/stworz")
//    public String addCourse(@RequestParam String name, @RequestParam String city) {
//        System.out.println("Course name: " + name + ", city: " + city);
//        return "course/created-course";
//    }

    @PostMapping("/dodaj-kurs")
    public String addCourse(@ModelAttribute Course course, @RequestParam String password, Model model) {
        model.addAttribute("cities", CITIES);
        model.addAttribute("courseModes", CourseMode.values());
        model.addAttribute("course", Course.builder().build());
        if (!course.getName().isEmpty() || !course.getCity().isEmpty() || course.getCourseMode() == CourseMode.NULL) {
            model.addAttribute("createdCourse", course);
        }
        System.out.println("Course " + course);
        courseService.saveCourse(course);
        return "course/add-course";
    }

//    @GetMapping("/dodaj-studenta")
//    public String addStudent(Model model) {
//        model.addAttribute("student", Student.builder().build());
//        model.addAttribute("courses", courses);
//        return "course/student-registration";
//    }
//
//    @PostMapping("/dodaj-studenta")
//    public String addStudent(@ModelAttribute Student student, Model model) {
//        System.out.println(student);
//        model.addAttribute("student", Student.builder().build());
//        model.addAttribute("courses", courses);
//
//        return "course/student-registration";
//    }

}
