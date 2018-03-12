package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.KalendarFromAndroid;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.KalendarResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.CalendarId;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.Kalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.UserModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.CalendarIdRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalendarRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KalendarService {
    @Autowired
    KalendarRepository kalendarRepository;

    @Autowired
    UserModelRepository userModelRepository;

    @Autowired
    CalendarIdRepository calendarIdRepository;

    public List<Kalendar> findKalendars(UserModel user) {
        return kalendarRepository.findKalendarsByUser(user);
    }

    public void saveKalendar(Kalendar kalendar, KalendarFromAndroid kalendarFromAndroid, String clientToken) {
        String idList = inputCalendarSetter(kalendarFromAndroid.getInputCalendarIds());
        kalendar.setOutputCalendarId(idList);
        kalendar.setOutputAccount(kalendarFromAndroid.getOutputCalendarId());
        kalendar.setUser(userModelRepository.findByClientToken(clientToken));
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
            kalendarResponse.setInputCalendarIds((setToStringCalendarIds(calendarIdRepository.findCalendarIdsByKalendar(kalendars.get(i)))));
            kalendarResponses.add(kalendarResponse);
        }
        return kalendarResponses;
    }

    public List<String> setToStringCalendarIds(List<CalendarId> calendarIds) {
        List<String> CalendarIDsToString = new ArrayList<>();
        for (int i = 0; i < calendarIds.size(); i++) {
            CalendarIDsToString.add(calendarIds.get(i).getId());
        }
        return CalendarIDsToString;
    }
}

