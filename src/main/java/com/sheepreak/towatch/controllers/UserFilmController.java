package com.sheepreak.towatch.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.sheepreak.towatch.models.User;
import com.sheepreak.towatch.models.UserFilm;
import com.sheepreak.towatch.repositories.FilmRepository;
import com.sheepreak.towatch.repositories.UserFilmRepository;
import com.sheepreak.towatch.repositories.UserRepository;
import com.sheepreak.towatch.services.UserFilmService;
import com.sheepreak.towatch.views.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserFilmController {

    @Autowired
    UserFilmService userFilmService;

    @PostMapping("/watch")
    @JsonView(Views.UserTurned.class)
    public ResponseEntity<User> watch(@RequestBody Map<String, String> json) {
        try {
            return new ResponseEntity<>(userFilmService.updateWatch(json.get("userid"), json.get("filmid"), true), HttpStatus.OK);
        } catch (NullPointerException npe) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/unwatch")
    @JsonView(Views.UserTurned.class)
    public ResponseEntity<User> unwatch(@RequestBody Map<String, String> json) {
        try {
            return new ResponseEntity<>(userFilmService.updateWatch(json.get("userid"), json.get("filmid"), false), HttpStatus.OK);
        } catch (NullPointerException npe) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/userfilms")
    @JsonView(Views.CollectionTurned.class)
    public ResponseEntity<List<UserFilm>> getAllInteractions() {
        return new ResponseEntity<>(userFilmService.findAll(), HttpStatus.OK);
    }
}
