package com.zezenk.mtrstest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistrationController {

    @RequestMapping({"", "/", "/registration"})
    public String getRegistrationPage(Model model) {
        return "registration";
    }
}
