package com.example.cinema.model;


import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.*;

@Entity
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_movie;
    private String title;
    private String description;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date release_date;
    private Integer duration;
    private String direction;

    @OneToMany(mappedBy = "movies" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Seances> seances;

    

    public Movies() {
    }

    public Movies(Long id_movie, String title, String description, Date release_date, Integer duration, String direction) {
        this.id_movie = id_movie;
        this.title = title;
        this.description = description;
        this.release_date = release_date;
        this.duration = duration;
        this.direction = direction;
    }

    public Long getId_movie() {
        return id_movie;
    }

    public void setId_movie(Long id_movie) {
        this.id_movie = id_movie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
