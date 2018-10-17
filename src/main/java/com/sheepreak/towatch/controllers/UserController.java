package com.sheepreak.towatch.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.sheepreak.towatch.models.Credentials;
import com.sheepreak.towatch.models.User;
import com.sheepreak.towatch.models.UserFilm;
import com.sheepreak.towatch.repositories.FilmRepository;
import com.sheepreak.towatch.repositories.UserRepository;
import com.sheepreak.towatch.views.Views;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserRepository userRepository;
    private final FilmRepository filmRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserRepository userRepository, FilmRepository filmRepository) {
        this.userRepository = userRepository;
        this.filmRepository = filmRepository;
    }

    @GetMapping("/users")
    @JsonView(Views.UserTurned.class)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    @JsonView(Views.UserTurned.class)
    public User getUserById(@PathVariable String id) {
        System.out.println(userRepository.findById(id).orElse(null).getFilms());
        return userRepository.findById(id).orElse(null);
    }

    @GetMapping("/user/name/{username}")
    @JsonView(Views.UserTurned.class)
    public User getUserByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }

    @PostMapping("/user")
    @JsonView(Views.UserTurned.class)
    public User postUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/user/films/{id}")
    @JsonView(Views.CollectionTurned.class)
    public List<UserFilm> getFilmsFromUserId(@PathVariable String id) {
        return userRepository.findById(id).get().getFilms();
    }

    @PostMapping("/user/{userName}/film/{filmName}")
    @JsonView(Views.UserTurned.class)
    public User addFilm(@PathVariable String userName, @PathVariable String filmName){
        User current = userRepository.findById(userName).orElse(null);
        UserFilm userFilm = new UserFilm();

        userFilm.setFilm(filmRepository.findById(filmName).orElse(null));
        userFilm.setWatched(false);
        current.add(userFilm);
        userFilm.setUser(current);
        System.out.println("------");
        current.getFilms().forEach(System.out::println);
        return userRepository.save(current);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody Credentials credentials) {
        User user = userRepository.findByUsername(credentials.getUsername());
        if(bCryptPasswordEncoder.matches(credentials.getPassword(), user.getPassword()))
            return new ResponseEntity<>(user,HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
