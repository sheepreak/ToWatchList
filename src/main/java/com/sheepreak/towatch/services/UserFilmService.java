package com.sheepreak.towatch.services;

import com.sheepreak.towatch.models.User;
import com.sheepreak.towatch.models.UserFilm;
import com.sheepreak.towatch.repositories.FilmRepository;
import com.sheepreak.towatch.repositories.UserFilmRepository;
import com.sheepreak.towatch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserFilmService {

    @Autowired
    UserFilmRepository userFilmRepository;

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    UserRepository userRepository;

    public User updateWatch(String userid, String filmid, boolean value) {
        User current = userRepository.findById(userid).orElseThrow(NullPointerException::new);
        UserFilm userFilm = userFilmRepository.findByFilmAndUser(filmRepository.findById(filmid).orElseThrow(NullPointerException::new), current).orElseThrow(NullPointerException::new);
        userFilm.setWatched(value);
        userFilmRepository.save(userFilm);
        return current;
    }

    public List<UserFilm> findAll() {
        return userFilmRepository.findAll();
    }
}
