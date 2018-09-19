package com.sheepreak.towatch;

import com.sheepreak.towatch.models.Film;
import com.sheepreak.towatch.models.User;
import com.sheepreak.towatch.repositories.FilmRepository;
import com.sheepreak.towatch.repositories.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
			Stream.of("Sheepreak", "Sypherin").forEach(name -> {
				User user = new User();
				user.setUsername(name);
				userRepository.save(user);
			});
			filmRepository.findAll().forEach(System.out::println);
			userRepository.findAll().forEach(System.out::println);
		};
	}
}
