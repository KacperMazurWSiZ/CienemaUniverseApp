package com.example.cinema.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
public class Seances {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_seance;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_movie" , nullable = false )
    private Movies movies;
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_room" , nullable = false )
    private Rooms rooms;
    private Integer price;
    private String seance_type;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date seance_datetime;

    @OneToMany(mappedBy = "seances" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reservations> reservations;
    
    public Seances() {
    }

    public Seances(Long id_seance, Movies movies, Rooms rooms, Integer price, String seance_type, Date seance_datetime) {
        this.id_seance = id_seance;
        this.movies = movies;
        this.rooms = rooms;
        this.price = price;
        this.seance_type = seance_type;
        this.seance_datetime = seance_datetime;
    }

    public Long getId_seance() {
        return id_seance;
    }

    public void setId_seance(Long id_seance) {
        this.id_seance = id_seance;
    }

    public Movies getMovies() {
        return movies;
    }

    public void setMovies(Movies movies) {
        this.movies = movies;
    }

    public Rooms getRooms() {
        return rooms;
    }

    public void setRooms(Rooms rooms) {
        this.rooms = rooms;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getSeance_type() {
        return seance_type;
    }

    public void setSeance_type(String seance_type) {
        this.seance_type = seance_type;
    }

    public Date getSeance_datetime() {
        return seance_datetime;
    }

    public void setSeance_datetime(Date seance_datetime) {
        this.seance_datetime = seance_datetime;
    }
}
