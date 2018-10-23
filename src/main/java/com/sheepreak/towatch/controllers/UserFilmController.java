package com.sheepreak.towatch.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.sheepreak.towatch.models.User;
import com.sheepreak.towatch.models.UserFilm;
import com.sheepreak.towatch.services.UserFilmService;
import com.sheepreak.towatch.views.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserFilmController {

    @Autowired
    UserFilmService userFilmService;

    @PostMapping("/watch")
    @JsonView(Views.UserTurned.class)
    public ResponseEntity<User> watch(@RequestBody Map<String, String> json) {
        try {
            return ResponseEntity.ok(userFilmService.updateWatch(json.get("userid"), json.get("filmid"), true));
        } catch (NullPointerException npe) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/unwatch")
    @JsonView(Views.UserTurned.class)
    public ResponseEntity<User> unwatch(@RequestBody Map<String, String> json) {
        try {
            return ResponseEntity.ok(userFilmService.updateWatch(json.get("userid"), json.get("filmid"), false));
        } catch (NullPointerException npe) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/userfilms")
    @JsonView(Views.CollectionTurned.class)
    public ResponseEntity<List<UserFilm>> getAllInteractions() {
        return ResponseEntity.ok(userFilmService.findAll());
    }

    @GetMapping("/watched/{userid}")
    @JsonView(Views.CollectionTurned.class)
    public ResponseEntity<List<UserFilm>> getAllWatchedByUser(@PathVariable String userid) {
        return ResponseEntity.ok(userFilmService.findByWatched(userid, true));
    }

    @GetMapping("/notwatched/{userid}")
    @JsonView(Views.CollectionTurned.class)
    public ResponseEntity<List<UserFilm>> getAllNotWatchedByUser(@PathVariable String userid) {
        return ResponseEntity.ok(userFilmService.findByWatched(userid, false));
    }

    @GetMapping("/countsubs/{filmid}")
    @JsonView(Views.CollectionTurned.class)
    public ResponseEntity countSubsForFilm(@PathVariable String filmid) {
        return ResponseEntity.ok(userFilmService.countSubs(filmid));
    }

    @GetMapping("/countwatched/{filmid}")
    @JsonView(Views.CollectionTurned.class)
    public ResponseEntity countWatchedForFilm(@PathVariable String filmid) {
        return ResponseEntity.ok(userFilmService.countWatched(filmid));
    }
}
