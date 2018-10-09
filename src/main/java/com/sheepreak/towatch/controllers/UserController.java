package com.sheepreak.towatch.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.sheepreak.towatch.models.User;
import com.sheepreak.towatch.models.UserFilm;
import com.sheepreak.towatch.repositories.FilmRepository;
import com.sheepreak.towatch.repositories.UserRepository;
import com.sheepreak.towatch.services.UserService;
import com.sheepreak.towatch.services.impl.UserServiceImpl;
import com.sheepreak.towatch.views.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @JsonView(Views.UserTurned.class)
    @PreAuthorize("hasRole('USER')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    @JsonView(Views.UserTurned.class)
    @PreAuthorize("hasRole('USER')")
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user/name/{username}")
    @JsonView(Views.UserTurned.class)
    @PreAuthorize("hasRole('USER')")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/user")
    @JsonView(Views.UserTurned.class)
    @PreAuthorize("hasRole('ADMIN')")
    public User postUser(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/user/films/{id}")
    @JsonView(Views.CollectionTurned.class)
    @PreAuthorize("hasRole('USER')")
    public List<UserFilm> getFilmsFromUserId(@PathVariable String id) {
        return userService.getFilmsFromUserId(id);
    }

    @PostMapping("/user/{userId}/film/{filmId}")
    @JsonView(Views.UserTurned.class)
    @PreAuthorize("hasRole('USER')")
    public User addFilm(@PathVariable String userId, @PathVariable String filmId){
        return userService.addFilm(userId, filmId);
    }

}
