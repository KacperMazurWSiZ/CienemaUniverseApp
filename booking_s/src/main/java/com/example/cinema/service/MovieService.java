package com.example.cinema.service;

import com.example.cinema.model.Movies;
import com.example.cinema.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MovieService {
    @Autowired
    private MovieRepository movie_repo;

    public List<Movies> listAll(){
        return movie_repo.findAll();
    }

    public void save(Movies movie){
        movie_repo.save(movie);
    }

    public Movies get(long id){
        return movie_repo.findById(id).get();
    }

    public void delete(long id){
        movie_repo.deleteById(id);
    }
}
