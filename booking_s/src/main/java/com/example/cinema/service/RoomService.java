package com.example.cinema.service;

import com.example.cinema.model.Rooms;
import com.example.cinema.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    @Transactional
    public class RoomService {
        @Autowired
        private RoomRepository room_repo;

        public List<Rooms> listAll(){
            return room_repo.findAll();
        }

        public void save(Rooms room){
            room_repo.save(room);
        }

        public Rooms get(long id){
            return room_repo.findById(id).get();
        }

        public void delete(long id){
            room_repo.deleteById(id);
        }
    }

