package com.greenfoxacademy.kalendaryo;

import com.greenfoxacademy.kalendaryo.model.entity.GoogleAuth;
import com.greenfoxacademy.kalendaryo.model.entity.KalUser;
import com.greenfoxacademy.kalendaryo.model.entity.Kalendar;
import com.greenfoxacademy.kalendaryo.service.AuthAndUserService;
import com.greenfoxacademy.kalendaryo.service.KalendarService;
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
    KalendarService kalendarService;

    @Override
    public void run(String... args) throws Exception {
        KalUser kalUser1 = new KalUser( authAndUserService.getRandomClientToken(),"test@email.com");
        KalUser kalUser2 = new KalUser( authAndUserService.getRandomClientToken(),"email@email.com");
        authAndUserService.saveKalUser(kalUser1);
        authAndUserService.saveKalUser(kalUser2);

        GoogleAuth googleAuth1 = new GoogleAuth(kalUser1.getUserEmail(), "Teszt Tamás");
        GoogleAuth googleAuth2 = new GoogleAuth("copy@email.com", "Teszt Tamás");
        authAndUserService.addUserToGoogleAuth(googleAuth1, kalUser1);
        authAndUserService.addUserToGoogleAuth(googleAuth2, kalUser1);

        Kalendar kalendar1 = new Kalendar(googleAuth1.getEmail(), "Work");
        Kalendar kalendar2 = new Kalendar(googleAuth2.getEmail(), "Birthday");
        kalendarService.addUserToKalendar(kalendar1, kalUser1);
        kalendarService.addUserToKalendar(kalendar2, kalUser1);
    }
}
