package com.sheepreak.towatch.services;

import com.sheepreak.towatch.models.Film;
import com.sheepreak.towatch.models.User;
import com.sheepreak.towatch.models.UserFilm;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User getUserById(String id);
    User getUserByUsername(String username);
    User save(User user);
    List<UserFilm> getFilmsFromUserId(String id);
    User addFilm(String userId, String filmId);
}
