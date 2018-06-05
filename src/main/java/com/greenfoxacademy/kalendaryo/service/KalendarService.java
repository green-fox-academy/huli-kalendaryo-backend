package com.greenfoxacademy.kalendaryo.service;


import com.github.javafaker.Faker;
import com.greenfoxacademy.kalendaryo.model.api.KalendarListResponse;
import com.greenfoxacademy.kalendaryo.model.entity.GoogleAuth;
import com.greenfoxacademy.kalendaryo.model.entity.GoogleCalendar;
import com.greenfoxacademy.kalendaryo.model.entity.KalUser;
import com.greenfoxacademy.kalendaryo.model.entity.Kalendar;
import com.greenfoxacademy.kalendaryo.service.authorization.Authorization;
import com.greenfoxacademy.kalendaryo.service.authorization.AuthorizeKal;
import com.greenfoxacademy.kalendaryo.exception.ValidationException;
import com.greenfoxacademy.kalendaryo.model.api.KalendarFromAndroid;
import com.greenfoxacademy.kalendaryo.model.api.KalendarResponse;
import com.greenfoxacademy.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.kalendaryo.repository.GoogleCalendarRepository;
import com.greenfoxacademy.kalendaryo.repository.KalendarRepository;
import com.greenfoxacademy.kalendaryo.repository.KalUserRepository;
import com.greenfoxacademy.kalendaryo.model.builder.KalendarResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class KalendarService {

    public static final String USER_NOT_FOUND_TOKEN = "User not found for clientToken=";
    public static final String USER_HAS_NO_ID = "ID not found for user with this clientToken=";
    public static final String NO_KALENDAR_FOR_USER = "Kalendar not found for user with this clientToken=";
    public static final String NO_GOOGLE_CALENDAR_FOR_KALENDAR = "Google calendar not found for kalendar with ID=";
    public static final String NO_USER_FOR_KALENDAR_ID = "User not found for kalendarId=";
    public static final String NO_KALENDAR_FOR_KALENDAR_ID = "Kalendar not found for kalendarId=";
    public static final String GOOGLE_AUTH_NOT_FOUND = "GoogleAuth not found int database for the following email and ID=";
    public static final String KAL_USER_HAS_NO_ID_OR_EMAIL = "ID or email not found for user of kalendar with ID=";
    public static final String KAL_USER_HAS_NO_ACCESS_TOKEN = "Access Token not found for user of kalendar with ID=";

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

    public void createNewKalendar(String clientToken, KalendarFromAndroid kalendarFromAndroid) throws ValidationException {
        Kalendar kalendar = new Kalendar();
        googleCalendarService.setGoogleCalendar(kalendar, kalendarFromAndroid, clientToken);
        authorizeKal.createGoogleCalendarUnderAccount(kalendarFromAndroid, kalendar);
    }

    public KalendarListResponse getKalendarsByClientToken(String clientToken) throws ValidationException {
        KalendarListResponse kalendarListResponse = new KalendarListResponse();
        List<KalendarResponse> kalendarResponse = setKalendarResponse(clientToken);
        kalendarListResponse.setKalendars(kalendarResponse);
        return kalendarListResponse;
    }

    public List<KalendarResponse> setKalendarResponse(String clientToken) throws ValidationException {
        List<Kalendar> kalendars = findKalendars(clientToken);
        List<KalendarResponse> kalendarResponses = new ArrayList<>();

        for (Kalendar actualKalendar : kalendars) {
            List<GoogleCalendar> googleCalendars = findGoogleCalendarsByKalendar(actualKalendar);
            List<String> namesOfGoogleCalendars = setToStringGoogleCalendars(googleCalendars);

            KalendarResponse kalendarResponse = new KalendarResponseBuilder()
                .setOutputCalendarId(actualKalendar.getName())
                .setOutputGoogleAuthId(actualKalendar.getOutputGoogleAuthId())
                .setInputGoogleCalendars(namesOfGoogleCalendars)
                .setId(actualKalendar.getId())
                .build();

            kalendarResponses.add(kalendarResponse);
        }
        return kalendarResponses;
    }

    private List<GoogleCalendar> findGoogleCalendarsByKalendar(Kalendar kalendar) throws ValidationException {
        try {
            return googleCalendarRepository.findGoogleCalendarsByKalendar(kalendar);
        } catch (NullPointerException ne) {
            throw new ValidationException(NO_GOOGLE_CALENDAR_FOR_KALENDAR + kalendar.getId());
        }
    }

    private List<Kalendar> findKalendars(String clientToken) throws ValidationException {
        try {
            KalUser user = getKalUserByClientToken(clientToken);
            return kalendarRepository.findKalendarsByUser(user);
        } catch (NullPointerException ne) {
            throw new ValidationException(NO_KALENDAR_FOR_USER + clientToken);
        }
    }

    public Kalendar setKalendarAttribute(Kalendar kalendar, KalendarFromAndroid kalendarFromAndroid, String clientToken) throws ValidationException {
        String customName = kalendarFromAndroid.getCustomName();

        if (StringUtils.isEmpty(customName)) {
            Faker faker = new Faker();
            kalendar.setName(faker.gameOfThrones().character());
        } else {
            kalendar.setName(kalendarFromAndroid.getCustomName());
        }
        kalendar.setOutputGoogleAuthId(kalendarFromAndroid.getOutputGoogleAuthId());
        kalendar.setUser(getKalUserByClientToken(clientToken));
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
            GoogleCalendarIDsToString.add(String.valueOf(googleCalendars.get(i).getId()));
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
            GoogleAuth googleAuth = findGoogleAuthByIdAndEmail(userId, userEmail);
            if(googleAuth.getAccessToken() != null) {
                return googleAuth.getAccessToken();
            } else {
                throw new ValidationException(KAL_USER_HAS_NO_ACCESS_TOKEN + kalendarId);
            }
        } catch (NullPointerException ne) {
            throw new ValidationException(KAL_USER_HAS_NO_ID_OR_EMAIL + kalendarId);
        }
    }

    private GoogleAuth findGoogleAuthByIdAndEmail(long userId, String userEmail) throws ValidationException {
        try {
            return googleAuthRepository.findByUser_IdAndEmail(userId, userEmail);
        } catch (NullPointerException ne) {
            throw new ValidationException(GOOGLE_AUTH_NOT_FOUND + userEmail + " " + userId);
        }
    }

    private String getCalendarIdByKalendarId(long kalendarId) throws ValidationException {
        try {
            Kalendar kalendarToDelete = findKalendarById(kalendarId);
            return kalendarToDelete.getGoogleCalendarId();
        } catch (NullPointerException ne) {
            throw new ValidationException(NO_GOOGLE_CALENDAR_FOR_KALENDAR + kalendarId);
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
            KalUser kalUserByClientToken = getKalUserByClientToken(clientToken);
            return kalUserByClientToken.getId();
        } catch (NullPointerException ne) {
            throw new ValidationException(USER_HAS_NO_ID + clientToken);
        }
    }

    private long getUserIdByKalendarId(long kalendarId) throws ValidationException {
        KalUser user = getKalUserByKalendarId(kalendarId);
        try {
            return user.getId();
        } catch (NullPointerException ne) {
            throw new ValidationException(USER_HAS_NO_ID + user.getClientToken());
        }
    }

    private KalUser getKalUserByKalendarId(long kalendarId) throws ValidationException {
        try {
            Kalendar kalendar = findKalendarById(kalendarId);
            return kalendar.getUser();
        } catch (NullPointerException ne) {
            throw new ValidationException(NO_USER_FOR_KALENDAR_ID + kalendarId);
        }
    }

    private Kalendar findKalendarById(long kalendarId) throws ValidationException {
        try {
            return kalendarRepository.findKalendarById(kalendarId);
        } catch (NullPointerException ne) {
            throw new ValidationException(NO_KALENDAR_FOR_KALENDAR_ID + kalendarId);
        }
    }

    private KalUser getKalUserByClientToken(String clientToken) throws ValidationException {
        try {
            return kalUserRepository.findByClientToken(clientToken);
        } catch (NullPointerException ne) {
            throw new ValidationException(USER_NOT_FOUND_TOKEN + clientToken);
        }
    }
}
