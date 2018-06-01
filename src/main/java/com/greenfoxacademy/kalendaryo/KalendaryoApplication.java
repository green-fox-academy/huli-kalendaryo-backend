package com.greenfoxacademy.kalendaryo;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

@SpringBootApplication
@Configuration
@ActiveProfiles("dev")
public class KalendaryoApplication implements CommandLineRunner {

	@Value("spring.datasource.username")
	private String username;

	@Value("spring.datasource.password")
	private String password;

	@Value("spring.datasource.url")
	private String url;

	public static void main(String[] args) {
		SpringApplication.run(KalendaryoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Flyway flyway = new Flyway();
		flyway.setDataSource(url, username, password);
		flyway.migrate();
	}
}
