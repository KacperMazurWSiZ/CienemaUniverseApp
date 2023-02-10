package com.example.cinema.service;

import com.example.cinema.model.Reservations;
import com.example.cinema.model.Seats;
import com.example.cinema.repository.ReservationsRepository;
import com.example.cinema.repository.SeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class ReservationService {

    @Autowired
    private ReservationsRepository reservation_repo;

    public List<Reservations> listAll(){
        return reservation_repo.findAll();
    }

    public void save(Reservations reservation){
        reservation_repo.save(reservation);
    }

    public Reservations get(long id){
        return reservation_repo.findById(id).get();
    }

    public void delete(long id){
        reservation_repo.deleteById(id);
    }


   
    
      public List<Reservations> getReservationsByUserEmail(String email) {
        return reservation_repo.findAllByUsersEmail(email);
      }
}
