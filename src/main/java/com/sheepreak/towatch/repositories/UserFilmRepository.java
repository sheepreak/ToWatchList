package com.sheepreak.towatch.repositories;

import com.sheepreak.towatch.models.Film;
import com.sheepreak.towatch.models.User;
import com.sheepreak.towatch.models.UserFilm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserFilmRepository extends CrudRepository<UserFilm, String> {
    UserFilm findByFilmAndUser(Optional<Film> film, Optional<User> user);
    List<UserFilm> findAll();
}
