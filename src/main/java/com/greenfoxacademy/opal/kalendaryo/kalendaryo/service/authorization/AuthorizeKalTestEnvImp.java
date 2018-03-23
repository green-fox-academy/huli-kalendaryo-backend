package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.authorization;

import com.google.api.client.util.Base64;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.GoogleAuth;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.Kalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalUserRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.SecureRandom;

@Component
@Profile("test")
public class AuthorizeKalTestEnvImp implements Authorization {

    @Autowired
    GoogleAuthRepository googleAuthRepository;

    @Autowired
    KalUserRepository kalUserRepository;

    @Autowired
    KalendarRepository kalendarRepository;

    public String getRandomToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] random = new byte[20];
        secureRandom.nextBytes(random);
        return Base64.encodeBase64String(random);
    }

    @Override
    public String authorize(String authCode) throws IOException {
        return getRandomToken();
    }
}