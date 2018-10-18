package com.sheepreak.towatch.controllers;

import com.sheepreak.towatch.models.Film;
import com.sheepreak.towatch.repositories.FilmRepository;
import com.sheepreak.towatch.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FilmController {

    @Autowired
    FilmService filmService;

    @GetMapping("/films")
    public ResponseEntity<List<Film>> getAllFilms() {
        return new ResponseEntity<>(filmService.getAllFilms(), HttpStatus.OK);
    }

    @GetMapping("/film/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(filmService.getFilmById(id),HttpStatus.OK);
        } catch (NullPointerException npe) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/film")
    public ResponseEntity<Film> postFilm(@RequestBody Film film) {
        return new ResponseEntity<>(filmService.save(film),HttpStatus.CREATED);
    }
}
