package com.sheepreak.towatch;

import com.sheepreak.towatch.models.Film;
import com.sheepreak.towatch.models.User;
import com.sheepreak.towatch.models.UserFilm;
import com.sheepreak.towatch.repositories.FilmRepository;
import com.sheepreak.towatch.repositories.UserFilmRepository;
import com.sheepreak.towatch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.stream.Stream;

@SpringBootApplication
public class TowatchApplication {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(TowatchApplication.class, args);
	}

	@Bean
	ApplicationRunner init(FilmRepository filmRepository, UserRepository userRepository, UserFilmRepository userFilmRepository) {
		return args -> {
			Stream.of("Titanic", "Star Wars", "Alien", "Jurassic Park", "Back to the future",
					"Gremlins", "Dr Strange", "Justice League", "Avengers").forEach(name -> {
				Film film = new Film();
				film.setName(name);
				filmRepository.save(film);
			});
			Film film = new Film();
			film.setName("Le soldat rose");
			filmRepository.save(film);
			Stream.of("Sheepreak", "Sypherin").forEach(name -> {
				User user = new User();
				user.setUsername(name);
				user.setPassword(bCryptPasswordEncoder.encode("password"));
				userRepository.save(user);
			});
			filmRepository.findAll().forEach(System.out::println);
			userRepository.findAll().forEach(System.out::println);
		};
	}

	@Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
    }

}
