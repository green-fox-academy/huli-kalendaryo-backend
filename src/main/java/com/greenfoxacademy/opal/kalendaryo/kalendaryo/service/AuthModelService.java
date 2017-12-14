package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.UserModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.AuthModelRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthModelService {

    @Autowired
    AuthModelRepository authModelRepository;

    public void saveAuthModel(AuthModel authModel) {
        authModelRepository.save(authModel);
    }

    public void getAuthModelByUser(String email) {
        authModelRepository.findAuthModelByEmail(email);
    }
}
