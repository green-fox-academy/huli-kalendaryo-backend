package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.UserModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.AuthModelRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

import static com.greenfoxacademy.opal.kalendaryo.kalendaryo.authorization.AuthorizeKal.authorize;

@Component
public class AuthAndUserService {

    @Autowired
    AuthModelRepository authModelRepository;

    @Autowired
    UserModelRepository userModelRepository;

    public void saveAuthModel(AuthModel authModel) throws IOException{
        String accessToken = authorize(authModel.getAuthCode());
        authModel.setAccessToken(accessToken);
        authModelRepository.save(authModel);
    }

    public AuthModel findAuthModelByEmail(String email) {
        return authModelRepository.findByEmail(email);
    }

    public UserModel findUserByClientToken(String clientToken) {
        return userModelRepository.findByClientToken(clientToken);
    }

    public void saveUserModel(UserModel userModel) {
        userModelRepository.save(userModel);
    }

    public UserModel setAndGetUserModel(@RequestBody AuthModel authModel) throws IOException {
        System.out.println(authModel.getAuthCode());
        String accessToken = authorize(authModel.getAuthCode());
        System.out.println(accessToken);
        AuthModel savedAuth = authModelRepository.findByEmail(authModel.getEmail());
        UserModel savedUser = userModelRepository.findById(savedAuth.getUser().getId());
  //      savedAuth.setAccessToken(savedUser.getAccessToken());
  //      savedUser.setEmailAndToken(savedAuth.getEmail(), accessToken);
        userModelRepository.save(savedUser);

        return savedUser;
    }
}
