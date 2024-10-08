package com.barreto.book_social_newtwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookSocialNewtworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookSocialNewtworkApplication.class, args);
	}

}
