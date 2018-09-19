package com.sheepreak.towatch.repositories;

import com.sheepreak.towatch.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@CrossOrigin(origins = "http://localhost:4200")
public interface UserRepository extends CrudRepository<User, String> {
    List<User> findAll();
    User findByUsername(String name);
}
