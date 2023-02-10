package com.example.cinema.repository;

import com.example.cinema.model.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservations, Long> {

     List<Reservations> findAllByUsersEmail(String email);

 

}

