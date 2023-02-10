package com.example.cinema.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Rooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_room;
    private String name_room;

    @OneToMany(mappedBy = "rooms" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
     private List<Seats> seats;

    @OneToMany(mappedBy = "rooms" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Seances> seances;

    public Rooms() {
    }

    public Rooms(Long id_room, String name_room) {
        this.id_room = id_room;
        this.name_room = name_room;
    }

    public Long getId_room() {
        return id_room;
    }

    public void setId_room(Long id_room) {
        this.id_room = id_room;
    }

    public String getName_room() {
        return name_room;
    }

    public void setName_room(String name_room) {
        this.name_room = name_room;
    }
}

