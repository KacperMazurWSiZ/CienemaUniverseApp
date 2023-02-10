package com.example.cinema.service;

import com.example.cinema.model.Seances;
import com.example.cinema.model.Seats;
import com.example.cinema.repository.SeanceRepository;
import com.example.cinema.repository.SeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SeanceService {

    @Autowired
    private SeanceRepository seance_repo;

    public List<Seances> listAll(){
        return seance_repo.findAll();
    }

    public void save(Seances seance){
        seance_repo.save(seance);
    }

    public Seances get(long id){
        return seance_repo.findById(id).get();
    }

    public void delete(long id){
        seance_repo.deleteById(id);
    }
}
