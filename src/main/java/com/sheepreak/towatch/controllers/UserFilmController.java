package com.sheepreak.towatch.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.sheepreak.towatch.models.User;
import com.sheepreak.towatch.models.UserFilm;
import com.sheepreak.towatch.repositories.FilmRepository;
import com.sheepreak.towatch.repositories.UserFilmRepository;
import com.sheepreak.towatch.repositories.UserRepository;
import com.sheepreak.towatch.views.Views;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserFilmController {

    private final UserFilmRepository userFilmRepository;
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;

    public UserFilmController(UserFilmRepository userFilmRepository, FilmRepository filmRepository, UserRepository userRepository) {
        this.userFilmRepository = userFilmRepository;
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/watched/{user}/{film}")
    @JsonView(Views.UserTurned.class)
    public User updateWatch(@PathVariable String user, @PathVariable String film) {
        Optional<User> current = userRepository.findById(user);
        UserFilm userFilm = userFilmRepository.findByFilmAndUser(filmRepository.findById(film), current);
        userFilm.setWatched(true);
        userFilmRepository.save(userFilm);
        return current.orElse(null);
    }

    @GetMapping("/userfilms")
    @JsonView(Views.CollectionTurned.class)
    public List<UserFilm> getAllInteractions() {
        return userFilmRepository.findAll();
    }
}
