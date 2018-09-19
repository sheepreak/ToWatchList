package com.sheepreak.towatch.controllers;

import com.sheepreak.towatch.models.Film;
import com.sheepreak.towatch.repositories.FilmRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FilmController {

    private final FilmRepository filmRepository;

    public FilmController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @GetMapping("/films")
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @GetMapping("/film/{id}")
    public Film getFilmById(@PathVariable String id) {
        return filmRepository.findById(id).orElse(null);
    }

    @PostMapping("/film")
    public Film postFilm(@RequestBody Film film) {
        return filmRepository.save(film);
    }
}
