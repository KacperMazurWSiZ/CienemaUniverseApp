
package com.example.cinema.controller;

import com.example.cinema.model.Rooms;
import com.example.cinema.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class RoomController {

    @Autowired
    RoomService service;

    @RequestMapping("/rooms")
    public String showRoomPage(Model model){
        List<Rooms> listRooms = service.listAll();
        model.addAttribute("listRooms", listRooms);
        return "Rooms/rooms";
    }

    @RequestMapping("/new_room")
    public String showNewRoomPage(Model model){
        Rooms rooms = new Rooms();
        model.addAttribute("rooms", rooms);

        return "Rooms/new_room";
    }
    
    @RequestMapping(value = "/save_room", method = RequestMethod.POST)
    public String saveRoom(@ModelAttribute("rooms") Rooms rooms) {
        service.save(rooms);

        return "redirect:/rooms";
    }
    
    @RequestMapping("/edit_room/{id_room}")
    public ModelAndView showEditRoomPage(@PathVariable(name = "id_room") int id_room) {
        ModelAndView mav_room = new ModelAndView("Rooms/edit_room");
        Rooms rooms = service.get(id_room);
        mav_room.addObject("rooms", rooms);

        return mav_room;
    }
    
    @RequestMapping("/delete_room/{id_room}")
    public String deleteRoom(@PathVariable(name = "id_room") int id_room) {
        service.delete(id_room);

        return "redirect:/rooms";
    }
}
