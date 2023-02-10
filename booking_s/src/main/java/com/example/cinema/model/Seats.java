package com.example.cinema.model;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class Seats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_seat;

     @ManyToOne  (fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
     @JoinColumn(name = "id_room" , nullable = false )
    private Rooms rooms;

    private String name;
    private Boolean is_free;

    @OneToMany(mappedBy = "seats" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reservations> reservations;

    public Seats() {
    }

    public Seats(Long id_seat, String name, Boolean is_free, Rooms rooms) {
        this.id_seat = id_seat;
        this.name = name;
        this.is_free = is_free;
        this.rooms = rooms;
    }

    public Long getId_seat() {
        return id_seat;
    }

    public void setId_seat(Long id_seat) {
        this.id_seat = id_seat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIs_free() {
        return is_free;
    }

    public void setIs_free(Boolean is_free) {
        this.is_free = is_free;
    }

    public Rooms getRooms() {
        return rooms;
    }

    public void setRooms(Rooms rooms) {
        this.rooms = rooms;
    }
}
