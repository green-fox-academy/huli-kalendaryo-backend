package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.github.javafaker.Faker;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.KalendarFromAndroid;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.KalendarResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.GoogleCalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.Kalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.KalUser;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.GoogleCalendarRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalendarRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalUserRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.authorization.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KalendarService {

    @Autowired
    KalUserRepository kalUserRepository;

    @Autowired
    Authorization authorization;

    @Autowired
    GoogleCalendarRepository googleCalendarRepository;

    @Autowired
    KalendarRepository kalendarRepository;

    public List<Kalendar> findKalendars(KalUser user) {
        return kalendarRepository.findKalendarsByUser(user);
    }

    public void setKalendar(Kalendar kalendar, KalendarFromAndroid kalendarFromAndroid, String clientToken) {
        Faker faker = new Faker();
        kalendar.setName(faker.gameOfThrones().character());
        kalendar.setOutputGoogleAuthId(kalendarFromAndroid.getOutputGoogleAuthId());
        kalendar.setUser(kalUserRepository.findByClientToken(clientToken));
        saveKalendar(kalendar);
    }

    public void saveKalendar(Kalendar kalendar) {
        kalendarRepository.save(kalendar);
    }

    public void addUserToKalendar(Kalendar kalendar, KalUser kalUser) {
        kalUser = kalUserRepository.findByUserEmail(kalUser.getUserEmail());
        kalendar.setUser(kalUser);
        saveKalendar(kalendar);
    }

    public List<KalendarResponse> setKalendarResponse(List<Kalendar> kalendars) {
        List<KalendarResponse> kalendarResponses = new ArrayList<>();
        for (int i = 0; i < kalendars.size(); i++) {
            KalendarResponse kalendarResponse = new KalendarResponse();
            kalendarResponse.setOutputGoogleAuthId(kalendars.get(i).getOutputGoogleAuthId());
            kalendarResponse.setOutputCalendarId(kalendars.get(i).getName());
            kalendarResponse.setInputGoogleCalendars((setToStringGoogleCalendars(googleCalendarRepository.findGoogleCalendarsByKalendar(kalendars.get(i)))));
            kalendarResponses.add(kalendarResponse);
        }
        return kalendarResponses;
    }

    public List<String> setToStringGoogleCalendars(List<GoogleCalendar> googleCalendars) {
        List<String> GoogleCalendarIDsToString = new ArrayList<>();
        for (int i = 0; i < googleCalendars.size(); i++) {
            GoogleCalendarIDsToString.add(googleCalendars.get(i).getId());
        }
        return GoogleCalendarIDsToString;
    }
}
