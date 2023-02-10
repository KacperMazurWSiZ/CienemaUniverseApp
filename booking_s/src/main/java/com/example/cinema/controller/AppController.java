package com.example.cinema.controller;

import com.example.cinema.model.Movies;
import com.example.cinema.model.Users;
import com.example.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        return "LoginAndRegister/login";
    }
    
}
