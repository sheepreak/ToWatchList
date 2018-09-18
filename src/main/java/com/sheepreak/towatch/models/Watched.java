package com.sheepreak.towatch.models;

import javax.persistence.*;

@Entity
@Table(name = "watched")
public class Watched {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id;

    @Column(name = "is_watched")
    private Boolean watched;

    public Watched() {
    }

    public Watched(Boolean watched) {
        this.watched = watched;
    }

    public Boolean getWatched() {
        return watched;
    }

    public void setWatched(Boolean watched) {
        this.watched = watched;
    }
}
