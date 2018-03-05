package com.greenfoxacademy.opal.kalendaryo.kalendaryo.testEnvironment;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.UserModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.AuthModelRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
//@Profile("test")
public class TestAuthAndUserService {

    @Autowired
    AuthModelRepository authModelRepository;

    @Autowired
    UserModelRepository userModelRepository;

    public void saveAuthModel(AuthModel authModel) {
        authModelRepository.save(authModel);
    }

    public void saveUserModel(UserModel userModel) {
        userModelRepository.save(userModel);
    }
}
