package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.GoogleAuth;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.Kalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.greenfoxacademy.opal.kalendaryo.kalendaryo.authorization.AuthorizeKal.authorize;

@Component
@Profile("dev")
public class SavingMethodsDevEnvImp implements SavingMethods {

    @Autowired
    GoogleAuthRepository googleAuthRepository;

    @Autowired
    KalendarRepository kalendarRepository;

    @Override
    public void saveGoogleAuth(GoogleAuth googleAuth) throws IOException {
        String accessToken = authorize(googleAuth.getAuthCode());
        googleAuth.setAccessToken(accessToken);
        googleAuthRepository.save(googleAuth);
    }

    @Override
    public void saveKalendar(Kalendar kalendar) {
        kalendarRepository.save(kalendar);
    }
}
