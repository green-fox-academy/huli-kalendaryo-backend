package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.google.api.client.util.Base64;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.MergedCalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.AuthModelRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.MergedCalendarRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
@Profile("test")
public class SavingMethodsTestEnvImp implements Authorization {

    @Autowired
    AuthModelRepository authModelRepository;

    @Autowired
    UserModelRepository userModelRepository;

    @Autowired
    MergedCalendarRepository mergedCalendarRepository;

    @Override
    public void saveAuthModel(AuthModel authModel) {
        if (authModelRepository.findByEmail(authModel.getEmail()) == null) {
            authModel.setAuthCode(getRandomToken());
            authModel.setAccessToken(getRandomToken());
            authModel.setRefreshToken(getRandomToken());
            authModel.setUser(userModelRepository.findByUserEmail(authModel.getUser().getUserEmail()));
            authModelRepository.save(authModel);
        }
    }

    @Override
    public void saveMergedCalendar(MergedCalendar mergedCalendar) {
        mergedCalendarRepository.save(mergedCalendar);
        //mergedCalendar.getUser().getMergedCalendarList().add(mergedCalendar);
    }

    public String getRandomToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] random = new byte[20];
        secureRandom.nextBytes(random);
        return Base64.encodeBase64String(random);
    }

}
