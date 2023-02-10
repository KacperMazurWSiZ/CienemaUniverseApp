package com.example.cinema.service;

import com.example.cinema.model.Seats;
import com.example.cinema.repository.SeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SeatService {
    @Autowired
    private SeatRepository seat_repo;

    public List<Seats> listAll(){
        return seat_repo.findAll();
    }

    public void save(Seats seat){
        seat_repo.save(seat);
    }

    public Seats get(long id){
        return seat_repo.findById(id).get();
    }

    public void delete(long id){
        seat_repo.deleteById(id);
    }

}