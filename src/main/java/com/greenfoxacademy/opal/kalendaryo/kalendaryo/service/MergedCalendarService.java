package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.UserModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.MergedCalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MergedCalendarService {
    @Autowired
    MergedCalendarRepository mergedCalendarRepository;

    public UserModel findMergedCalendars(UserModel user) {
        user.setMergedCalendarList(mergedCalendarRepository.findMergedCalendarsByUsername(user));
        return user;
    }
}
