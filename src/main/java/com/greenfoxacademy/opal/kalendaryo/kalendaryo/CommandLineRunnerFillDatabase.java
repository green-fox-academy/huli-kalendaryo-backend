package com.greenfoxacademy.opal.kalendaryo.kalendaryo;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.MergedCalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.UserModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.AuthAndUserService;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.CalendarIdService;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.MergedCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class CommandLineRunnerFillDatabase implements CommandLineRunner {

    @Autowired
    AuthAndUserService authAndUserService;

    @Autowired
    CalendarIdService calendarIdService;

    @Autowired
    MergedCalendarService mergedCalendarService;

    @Override
    public void run(String... args) throws Exception {
        UserModel userModel1 = new UserModel( authAndUserService.getRandomClientToken(),"test@email.com");
        UserModel userModel2 = new UserModel( authAndUserService.getRandomClientToken(),"email@email.com");
        authAndUserService.saveUserModel(userModel1);
        authAndUserService.saveUserModel(userModel2);

        AuthModel authModel1 = new AuthModel(userModel1.getUserEmail(), "Teszt Tamás");
        AuthModel authModel2 = new AuthModel("copy@email.com", "Teszt Tamás");
        authAndUserService.addUserToAuthModel(authModel1, userModel1);
        authAndUserService.addUserToAuthModel(authModel2, userModel1);

        MergedCalendar mergedCalendar1 = new MergedCalendar(authModel1.getEmail(), "outputcalid1");
        MergedCalendar mergedCalendar2 = new MergedCalendar(authModel2.getEmail(), "outputcalid2");
        mergedCalendarService.addUserToMergedCal(mergedCalendar1, userModel1);
        mergedCalendarService.addUserToMergedCal(mergedCalendar2, userModel1);
    }

}
