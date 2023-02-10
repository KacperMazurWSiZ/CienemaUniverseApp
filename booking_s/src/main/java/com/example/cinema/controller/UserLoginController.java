package com.example.cinema.controller;
import com.example.cinema.model.*;
import com.example.cinema.service.*;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.yaml.snakeyaml.events.Event;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class UserLoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private SeanceService service;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private SeatService serviceSeats;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @GetMapping("/login")
    public String showLoginForm() {
        return "LoginAndRegister/login";
    }

     @RequestMapping("/logout")
     public String returnToLoginForm() {
        return "LoginAndRegister/login";
     }

    @RequestMapping("/user_seance")
    public String showUserPage(HttpSession session,Model model){
        String email = (String) session.getAttribute("email");
        List<Seances> listSeances = service.listAll();
        model.addAttribute("listSeances", listSeances);
        Optional<Users> user = userService.findByEmail(email);
        if (user.isPresent()) {
            model.addAttribute("user_name", user.get().getFirst_name());
        }
        return "Login/user_seance";
    }

    @RequestMapping("/user_orders")
    public String showUserOrderPage(HttpSession session,Model model){
        String email = (String) session.getAttribute("email");
        List<Reservations> listReservations = reservationService.listAll();
        model.addAttribute("listReservations", listReservations);
        Optional<Users> user = userService.findByEmail(email);
        if (user.isPresent()) {
            model.addAttribute("user_name", user.get().getFirst_name());
        }
        return "Login/user_orders";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession session, Model model) {
        Optional<Users> user = userService.findByEmail(email);
        if (user.isPresent()) {
           if (bCryptPasswordEncoder.matches(password, user.get().getPassword())) {
                if (user.get().getRole().equals("admin")) {
                    return "Admin/admin_panel";
                } else if (user.get().getRole().equals("user")) {

                    String user_name = user.get().getFirst_name();
                    model.addAttribute("user_details",  user_name);
                    session.setAttribute("email", email);

                    return "Login/user_panel";
                }
            }
       }
        model.addAttribute("error", "Invalid email or password.");
        return "LoginAndRegister/login";
    }

    @RequestMapping("/buy_ticket/{id_seance}")
    public ModelAndView showBuyTicket(@PathVariable(name = "id_seance") int id_seance, Model model, HttpSession session) {
        ModelAndView mav_seance = new ModelAndView("Login/buy_ticket");
        Seances seances = service.get(id_seance);
        mav_seance.addObject("seances", seances);

        Reservations reservations = new Reservations();
        model.addAttribute("reservations", reservations);
        model.addAttribute("listUsers", userService.listAll());
        model.addAttribute("listSeances", service.listAll());
        model.addAttribute("listSeats", serviceSeats.listAll());

        String email = (String) session.getAttribute("email");
        Optional<Users> user = userService.findByEmail(email);
        if (user.isPresent()) {
            model.addAttribute("user_name", user.get().getFirst_name());
            model.addAttribute("user_id", user.get().getId_user());
            model.addAttribute("seance_room", seances.getRooms().getId_room());
        }

        return mav_seance;
    }

    @RequestMapping(value = "/save_ticket", method = RequestMethod.POST)
    public String saveReservation2(@ModelAttribute("reservations") Reservations reservations) {
        reservationService.save(reservations);
        Seats seat = serviceSeats.get(reservations.getSeats().getId_seat());
        seat.setIs_free(false);
        serviceSeats.save(seat);
        return "redirect:/user_orders";
    }
}