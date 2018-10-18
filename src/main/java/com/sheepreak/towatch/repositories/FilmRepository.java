package com.sheepreak.towatch.repositories;

import com.sheepreak.towatch.models.Film;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@CrossOrigin(origins = "http://localhost:4200")
public interface FilmRepository extends CrudRepository<Film, String> {
    List<Film> findAll();
    Optional<Film> findByName(String name);
}
