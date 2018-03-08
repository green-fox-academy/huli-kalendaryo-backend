package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.google.api.client.util.Base64;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.UserModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.AuthModelRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

import static com.greenfoxacademy.opal.kalendaryo.kalendaryo.authorization.AuthorizeKal.authorize;

@Service
public class AuthAndUserService {

    @Autowired
    AuthModelRepository authModelRepository;

    @Autowired
    UserModelRepository userModelRepository;

    @Autowired
    MockTokenGenerator mockTokenGenerator;

    public void saveAuthModel(AuthModel authModel) throws IOException{
        String accessToken = authorize(authModel.getAuthCode());
        authModel.setAccessToken(accessToken);
        authModelRepository.save(authModel);
    }

    public void saveMockAuthModel(AuthModel authModel) {
        if (authModelRepository.findAuthModelByEmail(authModel.getEmail()) == null) {
            saveUserModel(authModel.getUser());
            authModel.setAuthCode(mockTokenGenerator.generateMockToken());
            authModel.setAccessToken(mockTokenGenerator.generateMockToken());
            authModel.setRefreshToken(mockTokenGenerator.generateMockToken());
            setMockUser(authModel);
            authModelRepository.save(authModel);
        }
    }

    public void saveUserModel(UserModel userModel) {
        if (userModelRepository.findByClientToken(userModel.getClientToken()) == null) {
            userModelRepository.save(userModel);
        }
    }

    public UserModel findUserByClientToken(String clientToken) {
        return userModelRepository.findByClientToken(clientToken);
    }


    public void setMockUser(AuthModel authModel) {
        if (userModelRepository.findByClientToken(authModel.getUser().getClientToken()) != null) {
            UserModel user = userModelRepository.findByClientToken(authModel.getUser().getClientToken());
            authModel.setUser(user);
        }
    }

    public String getRandomClientToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] random = new byte[20];
        secureRandom.nextBytes(random);
        return Base64.encodeBase64String(random);
    }
}
