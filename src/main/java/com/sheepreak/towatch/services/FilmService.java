package com.sheepreak.towatch.services;

import com.sheepreak.towatch.models.Film;
import com.sheepreak.towatch.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class FilmService {

    @Autowired
    FilmRepository filmRepository;

    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    public Film getFilmById(String id) {
        return filmRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    public Film save(Film film) {
        return filmRepository.save(film);
    }

}
