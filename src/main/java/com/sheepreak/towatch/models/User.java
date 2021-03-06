package com.sheepreak.towatch.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.sheepreak.towatch.views.Views;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="User")
public class User implements Serializable {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonView({Views.UserTurned.class, Views.CollectionTurned.class})
	private String id;

	@NotNull
	@Column(name = "username")
	@JsonView({Views.UserTurned.class, Views.CollectionTurned.class})
	private String username;

	@NotNull
	@Column(name = "password")
	@JsonView({Views.UserTurned.class, Views.CollectionTurned.class})
	private String password;

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JsonView(Views.UserTurned.class)
    @JsonManagedReference
	private List<UserFilm> films = new ArrayList<>();

	public User() {
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<UserFilm> getFilms() {
		return films;
	}

	public void setFilms(List<UserFilm> films) {
		this.films = films;
	}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
	public String toString() {
		return username;
	}

	public void add(UserFilm userFilm) {
		films.add(userFilm);
	}
}
