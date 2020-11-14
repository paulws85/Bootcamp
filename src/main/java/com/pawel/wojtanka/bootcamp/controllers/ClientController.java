package com.pawel.wojtanka.bootcamp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/panel-klienta")
public class ClientController {

    @GetMapping("/")
    public String getClientDashboard() {
        return "client/client-panel";
    }

}
