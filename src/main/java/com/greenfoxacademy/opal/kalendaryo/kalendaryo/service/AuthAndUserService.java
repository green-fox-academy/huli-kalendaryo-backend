package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.google.api.client.util.Base64;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.GoogleAuth;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.KalUser;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalUserRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.authorization.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.SecureRandom;

@Service
public class AuthAndUserService{

    @Autowired
    GoogleAuthRepository googleAuthRepository;

    @Autowired
    KalUserRepository kalUserRepository;

    @Autowired
    Authorization authorization;

    public void saveKalUser(KalUser kalUser) {
        if (kalUserRepository.findByUserEmail(kalUser.getUserEmail()) != null) {
            kalUser = kalUserRepository.findByUserEmail(kalUser.getUserEmail());
        }
        kalUserRepository.save(kalUser);
    }

    public void addUserToGoogleAuth(GoogleAuth googleAuth, KalUser kalUser) throws IOException {
        googleAuth.setUser(kalUser);
        saveGoogleAuth(googleAuth);
    }

    public KalUser findUserByClientToken(String clientToken) {
        return kalUserRepository.findByClientToken(clientToken);
    }

    public String getRandomClientToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] random = new byte[20];
        secureRandom.nextBytes(random);
        return Base64.encodeBase64String(random);
    }

    public void saveGoogleAuth(GoogleAuth googleAuth) throws IOException {
        authorization.saveGoogleAuth(googleAuth);
    }

    public KalUser findUserByAuth(GoogleAuth googleAuth) {
        return kalUserRepository.findKalUserByGoogleAuthListIsContaining(googleAuth);
    }
}
