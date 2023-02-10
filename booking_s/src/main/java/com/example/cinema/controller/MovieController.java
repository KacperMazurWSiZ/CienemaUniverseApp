package com.example.cinema.controller;

import com.example.cinema.model.Movies;
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
public class MovieController {

    @Autowired MovieService service;


    @RequestMapping("/movies")
    public String showMoviePage(Model model){
        List<Movies> listMovies = service.listAll();
        model.addAttribute("listMovies", listMovies);

        return "Movies/movies";
    }

    @RequestMapping("/new_movie")
    public String showNewMoviePage(Model model){
        Movies movies = new Movies();
        model.addAttribute("movies", movies);

        return "Movies/new_movie";
    }

    @RequestMapping(value = "/save_movie", method = RequestMethod.POST)
    public String saveMovie(@ModelAttribute("movies") Movies movies) {
        service.save(movies);

        return "redirect:/movies";
    }

    @RequestMapping("/edit_movie/{id_movie}")
    public ModelAndView showEditMoviePage(@PathVariable(name = "id_movie") int id_movie) {
        ModelAndView mav_movie = new ModelAndView("Movies/edit_movie");
        Movies movies = service.get(id_movie);
        mav_movie.addObject("movies", movies);

        return mav_movie;
    }

    @RequestMapping("/delete_movie/{id_movie}")
    public String deleteMovie(@PathVariable(name = "id_movie") int id_movie) {
        service.delete(id_movie);
        return "redirect:/movies";
    }
}
