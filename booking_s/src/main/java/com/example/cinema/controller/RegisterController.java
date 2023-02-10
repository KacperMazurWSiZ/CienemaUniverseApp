package com.example.cinema.controller;

import com.example.cinema.model.Users;
import com.example.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    UserService service;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
       model.addAttribute("users", new Users());

        return "LoginAndRegister/signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(Users users) {

        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        users.setRole("user");
        service.save(users);

        return "LoginAndRegister/register_success";
    }
}
