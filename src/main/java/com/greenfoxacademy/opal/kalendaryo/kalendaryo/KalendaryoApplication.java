package com.greenfoxacademy.opal.kalendaryo.kalendaryo;

import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KalendaryoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KalendaryoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Flyway flyway = new Flyway();
		flyway.baseline();
		flyway.setBaselineOnMigrate(true);
		//flyway.setDataSource("jdbc:mysql://localhost:3306/kalendaryo_backend", "root", "12345");

		//flyway.setBaselineOnMigrate(true);
		//flyway.migrate();

	}
}
