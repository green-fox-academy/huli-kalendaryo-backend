package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.google.api.client.util.Base64;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.GoogleAuth;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.Kalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalUserRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
@Profile("test")
public class SavingMethodsTestEnvImp implements SavingMethods {

    @Autowired
    GoogleAuthRepository googleAuthRepository;

    @Autowired
    KalUserRepository kalUserRepository;

    @Autowired
    KalendarRepository kalendarRepository;

    @Override
    public void saveGoogleAuth(GoogleAuth googleAuth) {
        if (googleAuthRepository.findByEmail(googleAuth.getEmail()) == null) {
            googleAuth.setAuthCode(getRandomToken());
            googleAuth.setAccessToken(getRandomToken());
            googleAuth.setRefreshToken(getRandomToken());
            googleAuth.setUser(kalUserRepository.findByUserEmail(googleAuth.getUser().getUserEmail()));
            googleAuthRepository.save(googleAuth);
        }
    }

    @Override
    public void saveKalendar(Kalendar kalendar) {
        kalendarRepository.save(kalendar);
        //mergedCalendar.getUser().getMergedCalendarList().add(mergedCalendar);
    }

    public String getRandomToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] random = new byte[20];
        secureRandom.nextBytes(random);
        return Base64.encodeBase64String(random);
    }
}
