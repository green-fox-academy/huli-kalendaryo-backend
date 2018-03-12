package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.MergedCalendarFromAndroid;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.MergedCalendarResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.CalendarId;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.Kalendar;
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
    CalendarIdRepository calendarIdRepository;

    public List<Kalendar> findMergedCalendars(UserModel user) {
        return mergedCalendarRepository.findMergedCalendarsByUser(user);
    }

    public void saveMergedCalendar(Kalendar kalendar, MergedCalendarFromAndroid mergedCalendarFromAndroid, String clientToken) {
        String idList = inputCalendarSetter(mergedCalendarFromAndroid.getInputCalendarIds());
        kalendar.setOutputCalendarId(idList);
        kalendar.setOutputAccount(mergedCalendarFromAndroid.getOutputCalendarId());
        kalendar.setUser(userModelRepository.findByClientToken(clientToken));
        mergedCalendarRepository.save(kalendar);
    }

    private String inputCalendarSetter(String[] lists) {
        String string = "";
        for (String list : lists) {
            string += list;
        }
        return string;
    }

    public List<MergedCalendarResponse> setMergedCalendarResponse(List<Kalendar> kalendars) {
        List<MergedCalendarResponse> mergedCalendarResponses = new ArrayList<>();
        for (int i = 0; i < kalendars.size(); i++) {
            MergedCalendarResponse mergedCalendarResponse = new MergedCalendarResponse();
            mergedCalendarResponse.setOutputAccountId(kalendars.get(i).getOutputAccount());
            mergedCalendarResponse.setOutputCalendarId(kalendars.get(i).getOutputCalendarId());
            mergedCalendarResponse.setInputCalendarIds((setToStringCalendarIds(calendarIdRepository.findCalendarIdsByMergedCalendar(kalendars.get(i)))));
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

