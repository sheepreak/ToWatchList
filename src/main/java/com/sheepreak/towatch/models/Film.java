package com.sheepreak.towatch.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.sheepreak.towatch.views.Views;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="Film")
public class Film implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonView({Views.UserTurned.class, Views.CollectionTurned.class})
    private String id;

    @NotNull
    @Column(name = "name")
    @JsonView({Views.UserTurned.class, Views.CollectionTurned.class})
    private String name;

    public Film() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
