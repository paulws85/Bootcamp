package com.pawel.wojtanka.bootcamp.controllers.api;

import com.pawel.wojtanka.bootcamp.dto.CourseDto;
import com.pawel.wojtanka.bootcamp.service.ApiService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @GetMapping("/course/all")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<CourseDto> getCourses() {
        return apiService.getDetailedCourses();
    }

    @GetMapping("/course/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CourseDto getCourse(@PathVariable Long id) {
        return apiService.getDetailedCourse(id);
    }

}
