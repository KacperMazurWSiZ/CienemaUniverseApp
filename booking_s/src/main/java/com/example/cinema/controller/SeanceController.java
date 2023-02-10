package com.example.cinema.controller;

import com.example.cinema.model.Seances;
import com.example.cinema.model.Seats;
import com.example.cinema.service.MovieService;
import com.example.cinema.service.RoomService;
import com.example.cinema.service.SeanceService;
import com.example.cinema.service.SeatService;
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
public class SeanceController {

    @Autowired
    SeanceService service;

    @Autowired
    MovieService serviceMovie;

    @Autowired
    RoomService serviceRoom;

    @RequestMapping("/seances")
    public String showSeancesPage(Model model){
        List<Seances> listSeances = service.listAll();
        model.addAttribute("listSeances", listSeances);
        return "Seances/seances";
    }


    @RequestMapping("/new_seance")
    public String showNewSeancesPage(Model model){
        Seances seances = new Seances();
        model.addAttribute("seances", seances);
        model.addAttribute("listMovies", serviceMovie.listAll());
        model.addAttribute("listRooms", serviceRoom.listAll());

        return "Seances/new_seance";
    }

    @RequestMapping(value = "/save_seance", method = RequestMethod.POST)
    public String saveSeance(@ModelAttribute("seances") Seances seances) {
        service.save(seances);

        return "redirect:/seances";
    }

//     @RequestMapping("/edit_seance/{id_seance}")
//     public ModelAndView showEditSeancePage(@PathVariable(name = "id_seance") int id_seance) {
//         ModelAndView mav_seance = new ModelAndView("Seances/edit_seance");
//         Seances seances = service.get(id_seance);
//         mav_seance.addObject("seances", seances);
//         mav_seance.addObject("listMovies", serviceMovie.listAll());
//         mav_seance.addObject("listRooms", serviceRoom.listAll());
//
//
//         return mav_seance;
//     }

    @RequestMapping("/edit_seance/{id_seance}")
    public ModelAndView showEditSeancePage(@PathVariable(name = "id_seance") int id_seance) {
        ModelAndView mav_seance = new ModelAndView("Seances/edit_seance");
        Seances seances = service.get(id_seance);
        mav_seance.addObject("seances", seances);
        mav_seance.addObject("listMovies", serviceMovie.listAll());
        mav_seance.addObject("listRooms", serviceRoom.listAll());

        return mav_seance;
    }

    @RequestMapping("/delete_seance/{id_seance}")
    public String deleteSeance(@PathVariable(name = "id_seance") int id_seance) {
        service.delete(id_seance);
        return "redirect:/seances";
    }
}
