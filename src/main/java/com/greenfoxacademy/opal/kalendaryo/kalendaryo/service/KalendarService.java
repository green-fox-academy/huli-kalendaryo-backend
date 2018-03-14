package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.KalendarFromAndroid;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.KalendarResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.GoogleCalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.Kalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.KalUser;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.GoogleCalendarRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalendarRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KalendarService {
    @Autowired
    KalendarRepository kalendarRepository;

    @Autowired
    KalUserRepository kalUserRepository;

    @Autowired
    GoogleCalendarRepository googleCalendarRepository;

    public List<Kalendar> findKalendars(KalUser user) {
        return kalendarRepository.findKalendarsByUser(user);
    }

    public void saveKalendar(Kalendar kalendar, KalendarFromAndroid kalendarFromAndroid, String clientToken) {
        String idList = inputCalendarSetter(kalendarFromAndroid.getInputCalendarIds());
        kalendar.setOutputCalendarId(idList);
        kalendar.setOutputAccount(kalendarFromAndroid.getOutputCalendarId());
        kalendar.setUser(kalUserRepository.findByClientToken(clientToken));
        kalendarRepository.save(kalendar);
    }

    private String inputCalendarSetter(String[] lists) {
        String string = "";
        for (String list : lists) {
            string += list;
        }
        return string;
    }

    public List<KalendarResponse> setKalendarResponse(List<Kalendar> kalendars) {
        List<KalendarResponse> kalendarResponses = new ArrayList<>();
        for (int i = 0; i < kalendars.size(); i++) {
            KalendarResponse kalendarResponse = new KalendarResponse();
            kalendarResponse.setOutputAccountId(kalendars.get(i).getOutputAccount());
            kalendarResponse.setOutputCalendarId(kalendars.get(i).getOutputCalendarId());
            kalendarResponse.setInputCalendarIds((setToStringCalendarIds(googleCalendarRepository.findGoogleCalendarsByKalendar(kalendars.get(i)))));
            kalendarResponses.add(kalendarResponse);
        }
        return kalendarResponses;
    }

    public List<String> setToStringCalendarIds(List<GoogleCalendar> googleCalendars) {
        List<String> CalendarIDsToString = new ArrayList<>();
        for (int i = 0; i < googleCalendars.size(); i++) {
            CalendarIDsToString.add(googleCalendars.get(i).getId());
        }
        return CalendarIDsToString;
    }
}

