package com.example.demo.controller;

import com.example.model.User;
import com.example.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Controller
public class UserController {
    private UserServiceImpl userService;


    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String helloUser(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName()).get();
        String username = user.getUsername();
        String password = user.getPassword();
        String roles = user.getRolesString();
        model.addAttribute("id", user.getId());
        model.addAttribute("age", user.getAge());
        model.addAttribute("username",username);
        model.addAttribute("roles",roles);
        model.addAttribute("password",password);
        return "user";

    }
}
