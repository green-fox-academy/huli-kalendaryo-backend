package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.MergedCalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.AuthModelRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.MergedCalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static com.greenfoxacademy.opal.kalendaryo.kalendaryo.authorization.AuthorizeKal.authorize;

public class SavingMethodsDevEnvImp implements SavingMethods {

    @Autowired
    AuthModelRepository authModelRepository;

    @Autowired
    MergedCalendarRepository mergedCalendarRepository;

    @Override
    public void saveAuthModel(AuthModel authModel) throws IOException {
        String accessToken = authorize(authModel.getAuthCode());
        authModel.setAccessToken(accessToken);
        authModelRepository.save(authModel);
    }

    @Override
    public void saveMergedCalendar(MergedCalendar mergedCalendar) {
        mergedCalendarRepository.save(mergedCalendar);
    }

}
