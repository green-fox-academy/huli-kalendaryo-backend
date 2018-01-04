package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.google.api.client.util.Base64;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.UserModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.AuthModelRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.security.SecureRandom;

import static com.greenfoxacademy.opal.kalendaryo.kalendaryo.authorization.AuthorizeKal.authorize;
@Service
@Component
public class AuthAndUserService {

    @Autowired
    AuthModelRepository authModelRepository;

    @Autowired
    UserModelRepository userModelRepository;

    public void saveAuthModel(AuthModel authModel) {
        authModelRepository.save(authModel);
    }

    public AuthModel findAuthModelByEmail(String email) {
        return authModelRepository.findByEmail(email);
    }

    public void saveUserModel(UserModel userModel) {
        userModelRepository.save(userModel);
    }

    public UserModel getUserModel(@RequestBody AuthModel authModel) throws IOException {
        System.out.println(authModel.getAuthCode());
        String accessToken = authorize(authModel.getAuthCode());
        System.out.println(accessToken);
        AuthModel savedAuth = authModelRepository.findByEmail(authModel.getEmail());
        UserModel savedUser = userModelRepository.findById(savedAuth.getUser().getId());
        savedUser.setEmailAndToken(savedAuth.getEmail(), accessToken);
        userModelRepository.save(savedUser);
        return savedUser;
    }
    public String getRandomClientToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] random = new byte[20];
        secureRandom.nextBytes(random);
        return Base64.encodeBase64String(random);
    }

}
