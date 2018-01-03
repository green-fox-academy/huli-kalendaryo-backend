package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;


import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Service;

@Service
public class FlywayService {

        Flyway flyway = new Flyway();
        String url = "jdbc:mysql://" + System.getenv("RDS_HOSTNAME") + ":" + System.getenv("RDS_PORT") + "/" + System.getenv("RDS_DB_NAME");
        String username = System.getenv("RDS_USERNAME");
        String password = System.getenv("RDS_PASSWORD");

    public void flywayMigrate() {
		    flyway.setDataSource(url, username, password);
		    flyway.migrate();
        }
}
