package com.sheepreak.towatch.controllers;

import com.sheepreak.towatch.models.Film;
import com.sheepreak.towatch.repositories.FilmRepository;
import io.swagger.annotations.Authorization;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FilmController {

    private final FilmRepository filmRepository;

    public FilmController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/films")
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @GetMapping("/film/{id}")
    @Secured("ROLE_ADMIN")
    public Film getFilmById(@PathVariable String id) {
        return filmRepository.findById(id).orElse(null);
    }

    @PostMapping("/film")
    @Secured("ROLE_ADMIN")
    public Film postFilm(@RequestBody Film film) {
        return filmRepository.save(film);
    }
}
