package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;


import com.github.javafaker.Faker;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.exception.ValidationException;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.KalendarFromAndroid;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.KalendarListResponse;
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
import org.springframework.web.bind.annotation.RequestBody;

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

    @Autowired
    GoogleCalendarService googleCalendarService;

    public void createNewKalendar(String clientToken, KalendarFromAndroid kalendarFromAndroid) {
        Kalendar kalendar = new Kalendar();
        googleCalendarService.setGoogleCalendar(kalendar, kalendarFromAndroid, clientToken);
        authorizeKal.createGoogleCalendarUnderAccount(kalendarFromAndroid, kalendar);
    }

    public KalendarListResponse makeKalendarListResponse(String clientToken) {
        KalendarListResponse kalendarListResponse = new KalendarListResponse();
        List<KalendarResponse> kalendarResponse = setKalendarResponse(clientToken);
        kalendarListResponse.setKalendars(kalendarResponse);
        return kalendarListResponse;
    }

    private List<KalendarResponse> setKalendarResponse(String clientToken) {
        List<Kalendar> kalendars = findKalendars(clientToken);
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

    private List<Kalendar> findKalendars(String clientToken) {
        KalUser user = kalUserRepository.findByClientToken(clientToken);
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

    public List<String> setToStringGoogleCalendars(List<GoogleCalendar> googleCalendars) {
        List<String> GoogleCalendarIDsToString = new ArrayList<>();
        for (int i = 0; i < googleCalendars.size(); i++) {
            GoogleCalendarIDsToString.add(googleCalendars.get(i).getId());
        }
        return GoogleCalendarIDsToString;
    }

    public void deleteKalendar(String clientToken, long kalendarId) throws ValidationException {
        validateUser(clientToken, kalendarId);

        deleteGoogleCalendar(kalendarId);
        deleteKalendarById(kalendarId);
    }

    private void deleteGoogleCalendar(long kalendarId) throws ValidationException {
        String accessToken = getAccessTokenByKalendarId(kalendarId);
        String calendarId = getCalendarIdByKalendarId(kalendarId);
        authorizeKal.deleteCalendar(accessToken, calendarId);
    }


    private String getAccessTokenByKalendarId(long kalendarId) throws ValidationException {
        try {
            KalUser kalUser = getKalUserByKalendarId(kalendarId);
            long userId = kalUser.getId();
            String userEmail = kalUser.getUserEmail();
            GoogleAuth googleAuth = googleAuthRepository.findByUser_IdAndEmail(userId, userEmail);
            String accessToken = googleAuth.getAccessToken();
            return accessToken;
        } catch (NullPointerException ne) {
            throw new ValidationException("No such user in the database");
        }
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
    private void deleteKalendarById(long id) {
        googleCalendarRepository.deleteAllByKalendar_Id(id);
    }

    private void validateUser(String clientToken, long kalendarId) throws ValidationException {
        Long userIdByClientToken = getUserIdByClientToken(clientToken);
        Long userIdByKalendarId = getUserIdByKalendarId(kalendarId);

        if (userIdByClientToken != userIdByKalendarId) {
            throw new ValidationException("The kalendar does not belong to the user, " +
                "clientToken=" + clientToken + " kalendarId=" + kalendarId);
        };
    }

    private long getUserIdByClientToken(String clientToken) throws ValidationException {
        try {
            KalUser kalUserByClientToken = kalUserRepository.findByClientToken(clientToken);
            return kalUserByClientToken.getId();
        } catch (NullPointerException ne) {
            throw new ValidationException("User not found for clientToken=" + clientToken);
        }
    }

    private long getUserIdByKalendarId(long kalendarId) throws ValidationException {
        try {
            KalUser user = getKalUserByKalendarId(kalendarId);
            if (user != null) {
                return user.getId();
            } else {
                throw new ValidationException("User not found for kalendarId=" + kalendarId);
            }

        } catch (NullPointerException ne) {
            throw new ValidationException("Kalendar not found for kalendarId=" + kalendarId);
        }
    }

    private KalUser getKalUserByKalendarId(long kalendarId) {
        Kalendar kalendar = kalendarRepository.findKalendarById(kalendarId);
        return kalendar.getUser();
    }
}
