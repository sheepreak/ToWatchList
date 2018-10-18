package com.sheepreak.towatch.services;

import com.sheepreak.towatch.models.Credentials;
import com.sheepreak.towatch.models.User;
import com.sheepreak.towatch.models.UserFilm;
import com.sheepreak.towatch.repositories.FilmRepository;
import com.sheepreak.towatch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(NullPointerException::new);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<UserFilm> getFilmsFromUserId(String id) {
        return userRepository.findById(id).orElseThrow(NullPointerException::new).getFilms();
    }

    public User subscribe(String userid, String filmid){
        User current = userRepository.findById(userid).orElseThrow(NullPointerException::new);
        UserFilm userFilm = new UserFilm();
        userFilm.setFilm(filmRepository.findById(filmid).orElseThrow(NullPointerException::new));
        userFilm.setWatched(false);
        current.add(userFilm);
        userFilm.setUser(current);
        return userRepository.save(current);
    }

    public User login(Credentials credentials) {
        User user = userRepository.findByUsername(credentials.getUsername()).orElseThrow(NullPointerException::new);
        if(bCryptPasswordEncoder.matches(credentials.getPassword(), user.getPassword()))
            return user;
        else
            throw new IllegalArgumentException("bad credentials");
    }
}
