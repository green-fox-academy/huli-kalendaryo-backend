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
		/*String url = "jdbc:mysql://" + System.getenv("RDS_HOSTNAME") + ":" + System.getenv("RDS_PORT") + "/" + System.getenv("RDS_DB_NAME");*/
		flyway.setDataSource("jdbc:mysql://" + System.getenv("RDS_HOSTNAME") + ":" + System.getenv("RDS_PORT") + "/" + System.getenv("RDS_DB_NAME"), System.getenv("RDS_USERNAME"), System.getenv("RDS_PASSWORD"));
		flyway.migrate();
	}

}
