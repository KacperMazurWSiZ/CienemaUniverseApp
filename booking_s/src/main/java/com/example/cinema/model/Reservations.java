package com.example.cinema.model;

import jakarta.persistence.*;

@Entity
public class Reservations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reservation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user" , nullable = false )
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_seance" , nullable = false )
    private Seances seances;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_seat" , nullable = false )
    private Seats seats;

    public Reservations() {
    }

    public Reservations(Long id_reservation, Users users, Seances seances, Seats seats) {
        this.id_reservation = id_reservation;
        this.users = users;
        this.seances = seances;
        this.seats = seats;
    }

    public Long getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(Long id_reservation) {
        this.id_reservation = id_reservation;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Seances getSeances() {
        return seances;
    }

    public void setSeances(Seances seances) {
        this.seances = seances;
    }

    public Seats getSeats() {
        return seats;
    }

    public void setSeats(Seats seats) {
        this.seats = seats;
    }
}
