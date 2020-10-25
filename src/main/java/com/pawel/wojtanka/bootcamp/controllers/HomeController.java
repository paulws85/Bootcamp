package com.pawel.wojtanka.bootcamp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String home() {
//        System.out.println("Hello world!");
//        return "home";
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        System.out.println("Hello world!");
        return "redirect:kurs/lista";
    }

    @GetMapping(value = "strona-glowna")
    public String homePage() {
        System.out.println("Hello world!");
        return "forward:kurs/lista";
    }

}
