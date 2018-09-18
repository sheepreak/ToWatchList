package com.sheepreak.towatch.repositories;

import com.sheepreak.towatch.models.Film;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface FilmRepository extends CrudRepository<Film, String> {
    List<Film> findAll();
    Film findByName(String name);
}
