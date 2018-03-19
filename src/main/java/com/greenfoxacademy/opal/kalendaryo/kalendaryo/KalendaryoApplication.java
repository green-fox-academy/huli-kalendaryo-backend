package com.greenfoxacademy.opal.kalendaryo.kalendaryo;

import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

@SpringBootApplication
@Configuration
@ActiveProfiles(value = System.getenv("ACTIVE_PROFILE"))
public class KalendaryoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KalendaryoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Flyway flyway = new Flyway();
		String url = "jdbc:mysql://" + System.getenv("RDS_HOSTNAME") + ":" + System.getenv("RDS_PORT") + "/" + System.getenv("RDS_DB_NAME");
		String username = System.getenv("RDS_USERNAME");
		String password = System.getenv("RDS_PASSWORD");

		flyway.setDataSource(url, username, password);
		flyway.migrate();
	}
}
