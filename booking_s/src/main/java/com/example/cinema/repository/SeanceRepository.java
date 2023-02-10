package com.example.cinema.repository;

import com.example.cinema.model.Seances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeanceRepository extends JpaRepository<Seances, Long> {
}
