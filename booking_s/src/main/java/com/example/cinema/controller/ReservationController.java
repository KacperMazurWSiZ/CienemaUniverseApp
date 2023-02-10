package com.example.cinema.controller;

import com.example.cinema.model.Reservations;
import com.example.cinema.model.Seances;
import com.example.cinema.model.Seats;
import com.example.cinema.service.*;
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
public class ReservationController {

    @Autowired
    ReservationService service;

    @Autowired
    SeanceService serviceSeances;

    @Autowired
    SeatService serviceSeats;

    @Autowired
    UserService serviceUsers;


    @RequestMapping("/reservations")
    public String showReservationPage(Model model){
        List<Reservations> listReservations = service.listAll();
        model.addAttribute("listReservations", listReservations);
        return "Reservations/reservations";
    }


    @RequestMapping("/new_reservation")
    public String showNewReservationPage(Model model){
        Reservations reservations = new Reservations();
        model.addAttribute("reservations", reservations);
        model.addAttribute("listUsers", serviceUsers.listAll());
        model.addAttribute("listSeances", serviceSeances.listAll());
        model.addAttribute("listSeats", serviceSeats.listAll());
        return "Reservations/new_reservation";
    }

    @RequestMapping(value = "/save_reservation", method = RequestMethod.POST)
    public String saveReservation(@ModelAttribute("reservations") Reservations reservations) {
        service.save(reservations);
        Seats seat = serviceSeats.get(reservations.getSeats().getId_seat());
        seat.setIs_free(false);
       serviceSeats.save(seat);
        return "redirect:/reservations";
    }

    @RequestMapping("/edit_reservation/{id_reservation}")
    public ModelAndView showEditReservationPage(@PathVariable(name = "id_reservation") int id_reservation) {
        ModelAndView mav_reservation = new ModelAndView("Reservations/edit_reservation");
        Reservations reservations = service.get(id_reservation);
        mav_reservation.addObject("reservations", reservations);
        mav_reservation.addObject("listUsers", serviceUsers.listAll());
        mav_reservation.addObject("listSeances", serviceSeances.listAll());
        mav_reservation.addObject("listSeats", serviceSeats.listAll());

        return mav_reservation;
    }


    @RequestMapping("/delete_reservation/{id_reservation}")
    public String deleteReservation(@PathVariable(name = "id_reservation") int id_reservation) {
        Reservations reservation = service.get(id_reservation);
        service.delete(id_reservation);
        Seats seat = reservation.getSeats();
        seat.setIs_free(true);
        serviceSeats.save(seat);
        return "redirect:/reservations";
    }
}

