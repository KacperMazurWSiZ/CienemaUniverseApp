package com.example.cinema.controller;

import com.example.cinema.model.Rooms;
import com.example.cinema.model.Seats;
import com.example.cinema.model.Users;
import com.example.cinema.service.RoomService;
import com.example.cinema.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class SeatController {

    @Autowired
    SeatService service;
    @Autowired
    RoomService serviceRoom;

    @RequestMapping("/seats")
    public String showSeatPage(Model model){
        List<Seats> listSeats = service.listAll();
        model.addAttribute("listSeats", listSeats);
        return "Seats/seats";
    }

    @RequestMapping("/new_seat")
    public String showNewSeatPage(Model model){
        Seats seats = new Seats();
        seats.setIs_free(true);
        model.addAttribute("seats", seats);
        model.addAttribute("listRooms", serviceRoom.listAll());


        return "Seats/new_seat";
    }

    @RequestMapping(value = "/save_seat", method = RequestMethod.POST)
    public String saveSeat(@ModelAttribute("seats") Seats seats) {


        service.save(seats);

        return "redirect:/seats";
    }

    @RequestMapping("/edit_seat/{id_seat}")
    public ModelAndView showEditSeatPage(@PathVariable(name = "id_seat") int id_seat) {
        ModelAndView mav_seat = new ModelAndView("Seats/edit_seat");
        Seats seats = service.get(id_seat);
        mav_seat.addObject("seats", seats);

        return mav_seat;
    }

    @RequestMapping("/delete_seat/{id_seat}")
    public String deleteSeat(@PathVariable(name = "id_seat") int id_seat) {
        service.delete(id_seat);
        return "redirect:/seats";
    }

}
