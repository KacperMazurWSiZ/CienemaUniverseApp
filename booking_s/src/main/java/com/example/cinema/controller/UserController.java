package com.example.cinema.controller;

import com.example.cinema.model.Users;
import com.example.cinema.repository.UserRepository;
import com.example.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired UserService service;
    @RequestMapping("/users")
        public String showUserPage(Model model){
            List<Users> listUsers = service.listAll();
            model.addAttribute("listUsers", listUsers);

            return "Users/users";
        }

        @RequestMapping("/new_user")
        public String showNewUserPage(Model model){
            Users users = new Users();
            model.addAttribute("users", users);

            return "Users/new_user";
        }

        @RequestMapping(value = "/save_user", method = RequestMethod.POST)
        public String saveUser(@ModelAttribute("users") Users users) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(users.getPassword());
            users.setPassword(encodedPassword);
            users.setRole("user");
            service.save(users);

            return "redirect:/users";
        }

        @RequestMapping("/edit_user/{id_user}")
        public ModelAndView showEditUserPage(@PathVariable(name = "id_user") int id_user) {
            ModelAndView mav_user = new ModelAndView("Users/edit_user");
            Users users = service.get(id_user);
            mav_user.addObject("users", users);

            return mav_user;
        }

        @RequestMapping("/delete_user/{id_user}")
        public String deleteUser(@PathVariable(name = "id_user") int id_user) {
            service.delete(id_user);
            return "redirect:/users";
        }


}
