package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.MergedCalendarFromAndroid;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.MergedCalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.UserModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.MergedCalendarRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MergedCalendarService {
    @Autowired
    MergedCalendarRepository mergedCalendarRepository;

    @Autowired
    UserModelRepository userModelRepository;

    public UserModel findMergedCalendars(UserModel user) {
        user.setMergedCalendarList(mergedCalendarRepository.findMergedCalendarsByUser(user));
        return user;
    }

    public MergedCalendar settingNewMergedCalendar(MergedCalendarFromAndroid mergedCalendarFromAndroid, String clientToken) {
        MergedCalendar mergedCalendar = new MergedCalendar();
        for (int i = 0; i < mergedCalendarFromAndroid.getInputCalendarIds().length; i++) {
            mergedCalendar.setOutputAccount(mergedCalendarFromAndroid.getOutputCalendarId());
            mergedCalendar.setOutputCalendarId(mergedCalendarFromAndroid.getInputCalendarIds()[i]);
            mergedCalendar.setUser(userModelRepository.findByClientToken(clientToken));
        }
        return mergedCalendar;
    }

}
