package org.yoeltecleab.infinity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.yoeltecleab.infinity.service.UserServiceImpl;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    UserServiceImpl userService;


    @GetMapping("/")
    public String home(Principal user, Model model) {
        if (user == null) return "index";
        model.addAttribute("forName", "Hello " + userService.name(user.getName()));
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }


}
