package com.sheepreak.towatch.services;

import com.sheepreak.towatch.models.User;
import com.sheepreak.towatch.models.UserFilm;
import com.sheepreak.towatch.repositories.FilmRepository;
import com.sheepreak.towatch.repositories.UserFilmRepository;
import com.sheepreak.towatch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<UserFilm> findByWatched(String userId, boolean watched) {
        return userFilmRepository.findAll().stream().filter(u -> (u.isWatched() == watched && u.getUser().getId().equals(userId))).collect(Collectors.toList());
    }

    public long countSubs(String filmId) {
        return userFilmRepository.findAll().stream().filter(userFilm -> userFilm.getFilm().getId().equals(filmId)).count();
    }

    public long countWatched(String filmId) {
        return userFilmRepository.findAll().stream().filter(userFilm -> userFilm.getFilm().getId().equals(filmId) && userFilm.isWatched()).count();
    }
}
