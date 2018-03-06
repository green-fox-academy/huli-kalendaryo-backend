package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.AuthModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveAuthModelImpl {

    @Autowired
    AuthModelRepository authModelRepository;

    char[] mockAccessToken = new char[62];

    public void fillArraywithChars(char[] mockAccessToken) {
        int k = 0;
        for(int i = 0; i < 26; i++){
           mockAccessToken[i] = (char)(97 + (k++));
           mockAccessToken[i + 26] = (char)(65 + (k++));
        }
    }

    public void saveAuthModel(AuthModel authModel) {
        authModelRepository.save(authModel);
    }
}
