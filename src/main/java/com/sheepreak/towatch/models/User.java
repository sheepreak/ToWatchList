package com.sheepreak.towatch.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.sheepreak.towatch.views.Views;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    @Column(name="password")
    private String password;

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JsonView(Views.UserTurned.class)
	private List<UserFilm> films = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES", joinColumns = {
            @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID") })
    private Set<Role> roles;

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
	public String toString() {
		return username;
	}

	public void add(UserFilm userFilm) {
		films.add(userFilm);
	}
}
