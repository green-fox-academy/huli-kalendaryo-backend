package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.google.api.client.util.Base64;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.UserModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.AuthModelRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.SecureRandom;

@Service
public class AuthAndUserService{

    @Autowired
    AuthModelRepository authModelRepository;

    @Autowired
    UserModelRepository userModelRepository;

    @Autowired
    SavingMethods savingMethods;

    public void saveUserModel(UserModel userModel) {
        if (userModelRepository.findByUserEmail(userModel.getUserEmail()) != null) {
            userModel = userModelRepository.findByUserEmail(userModel.getUserEmail());
        }
        userModelRepository.save(userModel);
    }

    public void addUserToAuthModel(AuthModel authModel, UserModel userModel) throws IOException {
        authModel.setUser(userModel);
        saveAuthModel(authModel);
        //userModel.getAuthModelList().add(authModel);
    }

    public UserModel findUserByClientToken(String clientToken) {
        return userModelRepository.findByClientToken(clientToken);
    }

    public String getRandomClientToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] random = new byte[20];
        secureRandom.nextBytes(random);
        return Base64.encodeBase64String(random);
    }

    public void saveAuthModel(AuthModel authModel) throws IOException {
        savingMethods.saveAuthModel(authModel);
    }
}
