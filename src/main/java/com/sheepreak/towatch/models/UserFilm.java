package com.sheepreak.towatch.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.sheepreak.towatch.views.Views;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "user_film")
public class UserFilm implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.UserTurned.class, Views.CollectionTurned.class})
    private String id;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonView(Views.CollectionTurned.class)
    @JsonBackReference
    private User user;

    @ManyToOne(targetEntity = Film.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id")
    @JsonView({Views.UserTurned.class, Views.CollectionTurned.class})
    private Film film;

    @NotNull
    @Column(name = "watched")
    @JsonView({Views.UserTurned.class, Views.CollectionTurned.class})
    private boolean watched;

    public UserFilm() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
