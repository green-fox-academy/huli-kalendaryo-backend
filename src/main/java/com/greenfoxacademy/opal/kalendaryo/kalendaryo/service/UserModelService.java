package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.UserModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserModelService {

    @Autowired
    UserModelRepository userModelRepository;

    public void saveUserModel(UserModel userModel) {
        userModelRepository.save(userModel);
    }


}
