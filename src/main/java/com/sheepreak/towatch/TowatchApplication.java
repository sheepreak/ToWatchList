package com.sheepreak.towatch;

import com.sheepreak.towatch.models.Film;
import com.sheepreak.towatch.models.Role;
import com.sheepreak.towatch.models.User;
import com.sheepreak.towatch.repositories.FilmRepository;
import com.sheepreak.towatch.repositories.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@SpringBootApplication
public class TowatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(TowatchApplication.class, args);
	}

	@Bean
	ApplicationRunner init(FilmRepository filmRepository, UserRepository userRepository) {
		return args -> {
			Stream.of("Titanic", "Star Wars", "Alien", "Jurassic Park", "Back to the future",
					"Gremlins", "Dr Strange", "Justice League", "Avengers").forEach(name -> {
				Film film = new Film();
				film.setName(name);
				filmRepository.save(film);
			});
            Role roleUser = new Role();
            roleUser.setName("ROLE_USER");
            Role roleAdmin = new Role();
            roleAdmin.setName("ROLE_ADMIN");
            User user = new User();
            Set<Role> roles = new HashSet<>();
            roles.add(roleUser);
            user.setUsername("Sypherin");
            user.setPassword(new BCryptPasswordEncoder().encode("test"));
            user.setRoles(roles);
            userRepository.save(user);
            User user2 = new User();
            Set<Role> roles2 = new HashSet<>();
            roles.add(roleAdmin);
            user2.setUsername("Sheepreak");
            user2.setPassword(new BCryptPasswordEncoder().encode("test"));
            user2.setRoles(roles2);
            userRepository.save(user2);
			filmRepository.findAll().forEach(System.out::println);
			userRepository.findAll().forEach(System.out::println);
		};
	}
}
