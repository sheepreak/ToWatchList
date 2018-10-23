package com.sheepreak.towatch.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.sheepreak.towatch.models.Credentials;
import com.sheepreak.towatch.models.User;
import com.sheepreak.towatch.models.UserFilm;
import com.sheepreak.towatch.services.UserService;
import com.sheepreak.towatch.views.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    @JsonView(Views.UserTurned.class)
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/user/{id}")
    @JsonView(Views.UserTurned.class)
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (NullPointerException npe) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/name/{username}")
    @JsonView(Views.UserTurned.class)
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        try {
            return ResponseEntity.ok(userService.getUserByUsername(username));
        } catch (NullPointerException npe) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/user")
    @JsonView(Views.UserTurned.class)
    public ResponseEntity<User> postUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @GetMapping("/user/films/{id}")
    @JsonView(Views.CollectionTurned.class)
    public ResponseEntity<List<UserFilm>> getFilmsFromUserId(@PathVariable String id) {
        try {
            return ResponseEntity.ok(userService.getFilmsFromUserId(id));
        } catch (NullPointerException npe) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/user/subscribe")
    @JsonView(Views.UserTurned.class)
    public ResponseEntity<User> addFilm(@RequestBody Map<String, String> json){
        try {
            return ResponseEntity.ok(userService.subscribe(json.get("userid"), json.get("filmid")));
        } catch(NullPointerException npe) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    @JsonView(Views.UserTurned.class)
    public ResponseEntity<User> login(@RequestBody Credentials credentials) {
        try {
            return ResponseEntity.ok(userService.login(credentials));
        } catch (NullPointerException npe) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException iae) {
            //TODO voir comment passer un truc qui dit authentication failed
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

}
