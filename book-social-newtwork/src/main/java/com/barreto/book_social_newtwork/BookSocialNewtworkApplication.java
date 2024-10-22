package com.barreto.book_social_newtwork;

import com.barreto.book_social_newtwork.role.Role;
import com.barreto.book_social_newtwork.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing(
		auditorAwareRef = "auditorAware",
		dateTimeProviderRef = "dateTimeProvider"
)
@EnableAsync
public class BookSocialNewtworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookSocialNewtworkApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(RoleRepository roleRepository) {
		return args -> {
			if (roleRepository.findByName("USER").isEmpty()) {
				roleRepository.save(new Role("USER"));
			}
		};
	}

}
