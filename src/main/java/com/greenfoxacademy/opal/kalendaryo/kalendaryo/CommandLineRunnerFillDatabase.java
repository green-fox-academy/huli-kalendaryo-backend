package com.greenfoxacademy.opal.kalendaryo.kalendaryo;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.CalendarId;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.MergedCalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.UserModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.AuthAndUserService;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.CalendarIdService;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.SaveAuthModelImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommandLineRunnerFillDatabase implements CommandLineRunner {

    @Autowired
    AuthAndUserService authAndUserService;

    @Autowired
    SaveAuthModelImp saveAuthModel;

    @Autowired
    CalendarIdService calendarIdService;

    @Override
    public void run(String... args) throws Exception {
        UserModel userModel1 = new UserModel("clienttoken3", "haldirster@gamil.com");
        UserModel userModel2 = new UserModel("clienttoken5", "test@gustr.com");

        authAndUserService.saveUserModel(userModel1);
        authAndUserService.saveUserModel(userModel2);

        AuthModel authModel1 = new AuthModel("haldirster@gamil.com", "Teszt Tamás", userModel1);
        AuthModel authModel2 = new AuthModel("balazs.salfay@gmail.com", "Teszt Tamás", userModel1);
        saveAuthModel.saveAuthModel(authModel1);
        saveAuthModel.saveAuthModel(authModel2);

        MergedCalendar mergedCalendar1 = new MergedCalendar(userModel1, "haldirster@gamil.com", "outputcalid1");
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
