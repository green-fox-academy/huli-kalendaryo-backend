package com.greenfoxacademy.opal.kalendaryo.kalendaryo;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.CalendarId;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.MergedCalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.UserModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.AuthAndUserService;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.CalendarIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
// vagy ez @Component vagy a felette lévő
@Profile("test")
public class KalendaryoTestApplication implements CommandLineRunner {

    @Autowired
    AuthAndUserService authAndUserService;

    @Autowired
    CalendarIdService calendarIdService;

    @Override
    public void run(String... args) throws Exception {

        UserModel userModel1 = new UserModel();
        UserModel userModel2 = new UserModel();

        AuthModel authModel1 = new AuthModel("authEmail1", "authCode1","displayName1", userModel1, "accessToken1");
        AuthModel authModel2 = new AuthModel("authEmail2", "authCode2","displayName2", userModel1, "accessToken2");
        AuthModel authModel3 = new AuthModel("authEmail3", "authCode3","displayName3", userModel2, "accessToken3");
        AuthModel authModel4 = new AuthModel("authEmail4", "authCode4","displayName4", userModel2, "accessToken4");
        authAndUserService.saveAuthModel(authModel1);
        authAndUserService.saveAuthModel(authModel2);
        authAndUserService.saveAuthModel(authModel3);
        authAndUserService.saveAuthModel(authModel4);

        MergedCalendar mergedCalendar1 = new MergedCalendar();
        MergedCalendar mergedCalendar2 = new MergedCalendar();

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
