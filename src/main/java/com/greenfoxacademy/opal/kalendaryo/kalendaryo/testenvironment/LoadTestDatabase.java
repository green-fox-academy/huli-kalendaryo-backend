package com.greenfoxacademy.opal.kalendaryo.kalendaryo.testenvironment;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.CalendarId;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.MergedCalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.UserModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.AuthAndUserService;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.CalendarIdService;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class LoadTestDatabase implements CommandLineRunner {

    @Autowired
    AuthAndUserService authAndUserService;

    @Autowired
    CalendarIdService calendarIdService;

    @Override
    public void run(String... args) throws Exception {

        Flyway flyway = new Flyway();
        String url = "jdbc:mysql://" + System.getenv("RDS_HOSTNAME") + ":" + System.getenv("RDS_PORT") + "/" + System.getenv("RDS_DB_TEST_NAME");
        String username = System.getenv("RDS_USERNAME");
        String password = System.getenv("RDS_PASSWORD");

        flyway.setDataSource(url, username, password);
        flyway.migrate();

        UserModel userModel1 = new UserModel("clienttoken1", "dummy@jourrapide.com");
        UserModel userModel2 = new UserModel("clienttoken2", "test@gustr.com");

        AuthModel authModel1 = new AuthModel("dummy@jourrapide.com", "authCode1","Minta Marci", userModel1, "1/fFAGRNJru1FTz70BzhT3Zg");
        AuthModel authModel2 = new AuthModel("email@cuvox.de", "authCode2","Minta Marci", userModel1, "accessToken2");
        AuthModel authModel3 = new AuthModel("redriot@einrot.com", "authCode3","Valaki Vanda", userModel2, "accessToken3");
        AuthModel authModel4 = new AuthModel("test@gustr.com", "authCode4","Valaki Vanda", userModel2, "accessToken4");
        authAndUserService.saveAuthModel(authModel1);
        authAndUserService.saveAuthModel(authModel2);
        authAndUserService.saveAuthModel(authModel3);
        authAndUserService.saveAuthModel(authModel4);

        MergedCalendar mergedCalendar1 = new MergedCalendar(userModel1, "email@cuvox.de", "outputcalid1");
        MergedCalendar mergedCalendar2 = new MergedCalendar(userModel2, "test@gustr.com", "outputcalid2");

        CalendarId calendarId1 = new CalendarId("id1", authModel1, mergedCalendar1);
        CalendarId calendarId2 = new CalendarId("id2", authModel1, mergedCalendar2);
        CalendarId calendarId3 = new CalendarId("id3", authModel2, mergedCalendar1);
        CalendarId calendarId4 = new CalendarId("id4", authModel2, mergedCalendar2);
        calendarIdService.save(calendarId1);
        calendarIdService.save(calendarId2);
        calendarIdService.save(calendarId3);
        calendarIdService.save(calendarId4);
    }
}
