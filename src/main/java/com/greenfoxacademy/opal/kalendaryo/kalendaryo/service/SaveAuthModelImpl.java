package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.AuthModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class SaveAuthModelImpl {

    @Autowired
    AuthModelRepository authModelRepository;

    Random random = new Random();
    char[] chars =  {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x', 'y',
            'z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public void saveAuthModel(AuthModel authModel) {
        authModel.setMockAuthCode(chars, random);
        authModel.setMockAccessToken(chars, random);
        authModelRepository.save(authModel);
    }
}
