package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.google.api.client.util.Base64;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.GoogleAuth;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.KalUser;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.SecureRandom;

import static com.greenfoxacademy.opal.kalendaryo.kalendaryo.authorization.AuthorizeKal.authorize;

@Service
public class AuthAndUserService {

    @Autowired
    GoogleAuthRepository googleAuthRepository;

    @Autowired
    KalUserRepository kalUserRepository;

    public void saveGoogleAuth(GoogleAuth googleAuth) throws IOException{
        String accessToken = authorize(googleAuth.getAuthCode());
        googleAuth.setAccessToken(accessToken);
        googleAuthRepository.save(googleAuth);
    }

    public KalUser findUserByClientToken(String clientToken) {
        return kalUserRepository.findByClientToken(clientToken);
    }

    public void saveKalUser(KalUser kalUser) {
        kalUserRepository.save(kalUser);
    }

    public String getRandomClientToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] random = new byte[20];
        secureRandom.nextBytes(random);
        return Base64.encodeBase64String(random);
    }

    public KalUser findUserByAuth(GoogleAuth googleAuth) {
        return kalUserRepository.findKalUserByGoogleAuthListIsContaining(googleAuth);
    }
}
