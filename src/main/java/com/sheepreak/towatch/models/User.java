package com.sheepreak.towatch.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.sheepreak.towatch.views.Views;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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

//	@ElementCollection(targetClass = UserFilm.class, fetch = FetchType.LAZY)
//	@CollectionTable(name = "user_film", joinColumns = @JoinColumn(name = "user_id"))
//	@OneToMany(mappedBy = "film", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JsonView(Views.UserTurned.class)
//	private List<UserFilm> films = new ArrayList<>();

	@ElementCollection
	private Map<Film, Watched> films = new HashMap<>();

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

	public Map<Film, Watched> getFilms() {
		return films;
	}

	public void setFilms(Map<Film, Watched> films) {
		this.films = films;
	}

	@Override
	public String toString() {
		return username;
	}

}
