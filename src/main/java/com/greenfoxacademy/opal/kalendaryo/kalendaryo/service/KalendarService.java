package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;


import com.github.javafaker.Faker;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.exception.ValidationException;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.KalendarFromAndroid;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.KalendarResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.GoogleAuth;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.GoogleCalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.Kalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.KalUser;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.GoogleCalendarRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalendarRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalUserRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.authorization.Authorization;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.authorization.AuthorizeKal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Autowired
    AuthorizeKal authorizeKal;

    @Autowired
    GoogleAuthRepository googleAuthRepository;

    public List<Kalendar> findKalendars(KalUser user) {
        return kalendarRepository.findKalendarsByUser(user);
    }

    public Kalendar setKalendarAttribute(Kalendar kalendar, KalendarFromAndroid kalendarFromAndroid, String clientToken) {
        String customName = kalendarFromAndroid.getCustomName();

        if (StringUtils.isEmpty(customName)) {
            Faker faker = new Faker();
            kalendar.setName(faker.gameOfThrones().character());
        } else {
            kalendar.setName(kalendarFromAndroid.getCustomName());
        }
        kalendar.setOutputGoogleAuthId(kalendarFromAndroid.getOutputGoogleAuthId());
        kalendar.setUser(kalUserRepository.findByClientToken(clientToken));
        return kalendar;
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
            kalendarResponse.setId(kalendars.get(i).getId());
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


    public void deleteKalendar(String clientToken, long id) throws ValidationException {
        if (validateUser(clientToken, id)) {
            deleteGoogleCalendar(id);
            deleteKalendarById(id);
        } else {
            throw new ValidationException("The validation process failed");
        }
    }

    private void deleteGoogleCalendar(long id) throws ValidationException {
        String accesToken = getAccessTokenByKalendarId(id);
        String calendarId = getCalendarIdByKalendarId(id);
        authorizeKal.deleteCalendar(accesToken, calendarId);
    }

    private String getCalendarIdByKalendarId(long id) throws ValidationException {
        try {
            Kalendar kalendarToDelete = kalendarRepository.findKalendarById(id);
            String googleCalendarId = kalendarToDelete.getGoogleCalendarId();
            return googleCalendarId;
        } catch (NullPointerException ne) {
            throw new ValidationException("No such Google calendar id in the database");
        }
    }

    private String getAccessTokenByKalendarId(long id) throws ValidationException {
        try {
            Kalendar kalendarToDelete = kalendarRepository.findKalendarById(id);
            KalUser kalUser = kalendarToDelete.getUser();
            long userId = kalUser.getId();
            String userEmail = kalUser.getUserEmail();
            GoogleAuth googleAuth = googleAuthRepository.findByUser_IdAndEmail(userId, userEmail);
            String accessToken = googleAuth.getAccessToken();
            return accessToken;
        } catch (NullPointerException ne) {
            throw new ValidationException("No such user in the database");
        }
    }

    private void deleteKalendarById(long id) {
        googleCalendarRepository.deleteAllByKalendar_Id(id);
    }

    private boolean validateUser(String clientToken, long kalendarId) throws ValidationException {
        Long kalUserId = getKalUserId(clientToken);
        Long userId = getUserIdOfKalendar(kalendarId);

        return kalUserId == userId;
    }

    private long getKalUserId(String clientToken) throws ValidationException {
        try {
            KalUser userByToken = kalUserRepository.findByClientToken(clientToken);
            Long kalUserId = userByToken.getId();
            return kalUserId;
        } catch (NullPointerException ne) {
            throw new ValidationException("No such user in the database");
        }
    }

    private long getUserIdOfKalendar(long kalendarId) throws ValidationException {
        try {
            Kalendar deletableKalendar = kalendarRepository.findKalendarById(kalendarId);
            KalUser userOfTheKalendar = deletableKalendar.getUser();
            Long userId = userOfTheKalendar.getId();
            return userId;
        } catch (NullPointerException ne) {
            throw new ValidationException("No such kalendar in the database");
        }
    }
}
