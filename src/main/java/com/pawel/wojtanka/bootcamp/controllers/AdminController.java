package com.pawel.wojtanka.bootcamp.controllers;

import com.pawel.wojtanka.bootcamp.model.Rule;
import com.pawel.wojtanka.bootcamp.model.Student;
import com.pawel.wojtanka.bootcamp.service.CourseService;
import com.pawel.wojtanka.bootcamp.service.RuleService;
import com.pawel.wojtanka.bootcamp.service.StudentService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
public class AdminController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final RuleService ruleService;

    @GetMapping(value = "/")
    public String getAdminPanel() {
        return "admin/admin-panel";
    }

    @GetMapping(value = "/uzytkownicy/lista")
    public String getStudentList(Model model) {
        model.addAttribute("userList", studentService.findAllUsers());

        return "admin/user/list";
    }

    @GetMapping(value = "/uzytkownicy/usun/{id}")
    public String deleteUser(@PathVariable(name = "id") long id) {
        studentService.deleteUser(id);

        return "redirect:/admin/uzytkownicy/lista";
    }

    @GetMapping(value = "/uzytkownicy/edytuj/{id}")
    public String editUser(@PathVariable(name = "id") long id, Model model, final HttpSession session) {
        Student user = studentService.findUser(id);
        session.setAttribute("userId", user.getId());
        session.setAttribute("userRule", user.getRule());
        model.addAttribute("user", user);
        model.addAttribute("ruleList", ruleService.findAllRules());
        model.addAttribute("courseList", courseService.findAllCourses());

        return "admin/user/edit-user";
    }

    @PostMapping(value = "/uzytkownicy/edytuj/{id}")
    public String userEdited(@PathVariable(name = "id") long id, @ModelAttribute Student user, final HttpServletRequest httpServletRequest) {
        if ((Long) httpServletRequest.getSession().getAttribute("userId") == id) {
            user.setId(id);
            Rule rule = (Rule) httpServletRequest.getSession().getAttribute("userRule");
            user.setRule(rule);
            studentService.saveStudent(user);
        }
        ;
        httpServletRequest.getSession().invalidate(); //wywalanie wszystkiego z sesjii

        return "redirect:/admin/uzytkownicy/lista";
    }

}
