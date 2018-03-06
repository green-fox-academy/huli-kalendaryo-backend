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

    public MergedCalendar settingNewMergedCalendar(MergedCalendar mergedCalendar, MergedCalendarFromAndroid mergedCalendarFromAndroid, String clientToken) {
        String idList = inputCalendarSetter(mergedCalendarFromAndroid.getInputCalendarIds());
        mergedCalendar.setOutputCalendarId(idList);
        mergedCalendar.setOutputAccount(mergedCalendarFromAndroid.getOutputCalendarId());
        mergedCalendar.setUser(userModelRepository.findByClientToken(clientToken));
        return mergedCalendar;
    }

    private String inputCalendarSetter(String[] lists) {
        String string = "";
        for (String list : lists) {
            string += list;
        }
        return string;
    }

}
