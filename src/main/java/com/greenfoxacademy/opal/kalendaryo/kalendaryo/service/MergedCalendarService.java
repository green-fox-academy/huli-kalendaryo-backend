package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.MergedCalendarFromAndroid;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.MergedCalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.UserModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.MergedCalendarRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.List;

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
        String idList = fieldInputcalendarSetter(mergedCalendarFromAndroid.getInputCalendarIds());
        mergedCalendar.setOutputCalendarId(idList);
        mergedCalendar.setOutputAccount(mergedCalendarFromAndroid.getOutputCalendarId());
        mergedCalendar.setUser(userModelRepository.findByClientToken(clientToken));
        return mergedCalendar;
    }

    private String fieldInputcalendarSetter(String[] lists) {
        String string = "";
        for (int i = 0; i < lists.length; i++) {
            string += lists[i];
        }
        return string;
    }

}
