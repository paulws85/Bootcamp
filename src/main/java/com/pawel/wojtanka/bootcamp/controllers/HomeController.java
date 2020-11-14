package com.pawel.wojtanka.bootcamp.controllers;

import com.pawel.wojtanka.bootcamp.repository.CourseRepository;
import com.pawel.wojtanka.bootcamp.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class HomeController {

    private final CourseService courseService;

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String home() {
//        System.out.println("Hello world!");
//        return "home";
//    }

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String home() {
//        System.out.println("Hello world!");
//        return "redirect:kurs/lista";
//    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("courseList", courseService.findAllCourses());

        return "course/list";
    }

    @GetMapping(value = "strona-glowna")
    public String homePage() {
        System.out.println("Hello world!");
        return "forward:kurs/lista";
    }

}
