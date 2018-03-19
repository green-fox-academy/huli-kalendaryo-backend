package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.MergedCalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.AuthModelRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.MergedCalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


import java.io.IOException;

import static com.greenfoxacademy.opal.kalendaryo.kalendaryo.authorization.AuthorizeKal.authorize;

@Component
@Profile("dev")
public class SavingMethodsDevEnvImp implements Authorization {

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
