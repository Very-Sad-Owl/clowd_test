package com.boots.controller;

import com.boots.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String get(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

}
