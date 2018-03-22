package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.github.javafaker.Faker;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.MergedCalendarFromAndroid;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.MergedCalendarResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.CalendarId;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.MergedCalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.UserModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.CalendarIdRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.MergedCalendarRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MergedCalendarService {
    @Autowired
    MergedCalendarRepository mergedCalendarRepository;

    @Autowired
    UserModelRepository userModelRepository;

    @Autowired
    SavingMethods savingMethods;

    @Autowired
    CalendarIdRepository calendarIdRepository;

    public List<MergedCalendar> findMergedCalendars(UserModel user) {
        return mergedCalendarRepository.findMergedCalendarsByUser(user);
    }
  
    public void setMergedCalendar(MergedCalendar mergedCalendar, MergedCalendarFromAndroid mergedCalendarFromAndroid, String clientToken) {
        Faker faker = new Faker();
        mergedCalendar.setOutputCalendarId(faker.gameOfThrones().character());    
        mergedCalendar.setOutputAccount(mergedCalendarFromAndroid.getOutputCalendarId());
        mergedCalendar.setUser(userModelRepository.findByClientToken(clientToken));
        saveMergedCalendar(mergedCalendar);
    }

    public void saveMergedCalendar(MergedCalendar mergedCalendar) {
        savingMethods.saveMergedCalendar(mergedCalendar);
    }

    public void addUserToMergedCal(MergedCalendar mergedCalendar, UserModel userModel) {
        userModel = userModelRepository.findByUserEmail(userModel.getUserEmail());
        mergedCalendar.setUser(userModel);
        saveMergedCalendar(mergedCalendar);
    }



    public List<MergedCalendarResponse> setMergedCalendarResponse(List<MergedCalendar> mergedCalendars) {
        List<MergedCalendarResponse> mergedCalendarResponses = new ArrayList<>();
        for (int i = 0; i < mergedCalendars.size(); i++) {
            MergedCalendarResponse mergedCalendarResponse = new MergedCalendarResponse();
            mergedCalendarResponse.setOutputAccountId(mergedCalendars.get(i).getOutputAccount());
            mergedCalendarResponse.setOutputCalendarId(mergedCalendars.get(i).getOutputCalendarId());
            mergedCalendarResponse.setInputCalendarIds((setToStringCalendarIds(calendarIdRepository.findCalendarIdsByMergedCalendar(mergedCalendars.get(i)))));
            mergedCalendarResponses.add(mergedCalendarResponse);
        }
        return mergedCalendarResponses;
    }

    public List<String> setToStringCalendarIds(List<CalendarId> calendarIds) {
        List<String> CalendarIDsToString = new ArrayList<>();
        for (int i = 0; i < calendarIds.size(); i++) {
            CalendarIDsToString.add(calendarIds.get(i).getId());
        }
        return CalendarIDsToString;
    }
}

