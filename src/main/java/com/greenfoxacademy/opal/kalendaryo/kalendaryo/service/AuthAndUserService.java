package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.google.api.client.util.Base64;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.KalUser;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.AuthModelRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.SecureRandom;

import static com.greenfoxacademy.opal.kalendaryo.kalendaryo.authorization.AuthorizeKal.authorize;

@Service
public class AuthAndUserService {

    @Autowired
    AuthModelRepository authModelRepository;

    @Autowired
    KalUserRepository kalUserRepository;

    public void saveAuthModel(AuthModel authModel) throws IOException{
        String accessToken = authorize(authModel.getAuthCode());
        authModel.setAccessToken(accessToken);
        authModelRepository.save(authModel);
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

    public KalUser findUserByAuth(AuthModel authModel) {
        return kalUserRepository.findKalUserByAuthModelListIsContaining(authModel);
    }
}
