package com.greenfoxacademy.kalendaryo.service.authorization;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.common.collect.Lists;
import com.greenfoxacademy.kalendaryo.model.entity.Kalendar;
import com.greenfoxacademy.kalendaryo.service.AuthAndUserService;
import com.greenfoxacademy.kalendaryo.model.api.KalendarFromAndroid;
import com.greenfoxacademy.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.kalendaryo.service.KalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import com.google.api.services.calendar.model.Calendar;

import java.io.IOException;
import java.util.List;

@Service
@Profile("dev")
public class AuthorizeKal implements Authorization{

    private static final String APPLICATION_NAME = "Kalendaryo";
    private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"), ".credentials/calendar-java-quickstart");
    private static FileDataStoreFactory DATA_STORE_FACTORY;
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static HttpTransport HTTP_TRANSPORT;
    private com.google.api.services.calendar.Calendar calendarClient;
    List<Calendar> addedCalendarUsingBatch = Lists.newArrayList();

    @Autowired
    GoogleAuthRepository googleAuthRepository;

    @Autowired
    KalendarService kalendarService;

    @Autowired
    AuthAndUserService authAndUserService;

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    public static HttpResponse executeGet(
            HttpTransport transport, JsonFactory jsonFactory, String accessToken, GenericUrl url)
            throws IOException {
        Credential credential =
                new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessToken);
        HttpRequestFactory requestFactory = transport.createRequestFactory(credential);
        return requestFactory.buildGetRequest(url).execute();
    }

    public String authorize(String authCode) throws IOException {
        String clientId = System.getenv("CLIENT_ID");
        String clientSecret = System.getenv("CLIENT_SECRET");
        GoogleTokenResponse tokenResponse =
                new GoogleAuthorizationCodeTokenRequest(
                        new NetHttpTransport(),
                        JSON_FACTORY,
                        "https://www.googleapis.com/oauth2/v4/token",
                        clientId,
                        clientSecret,
                        authCode,
                        "https://huli-kalendaryo-android.firebaseapp.com/__/auth/handler")
                        .execute();

        return tokenResponse.getAccessToken();
    }

    public void createGoogleCalendarUnderAccount(KalendarFromAndroid kalendarFromAndroid, Kalendar kalendar) {
        try {
            String accessToken = googleAuthRepository.findByEmail(kalendarFromAndroid.getOutputGoogleAuthId()).getAccessToken();
            Credential credential =
                    new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessToken);
            calendarClient = new com.google.api.services.calendar.Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                    .setApplicationName(APPLICATION_NAME).build();

            com.google.api.services.calendar.model.Calendar calendar = new Calendar();
            calendar.setSummary(kalendar.getName());

            Calendar createdCalendar = calendarClient.calendars().insert(calendar).execute();
            kalendar.setGoogleCalendarId(createdCalendar.getId());
            kalendarService.saveKalendar(kalendar);
            getInputCalendarsData(calendarClient);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void getInputCalendarsData (com.google.api.services.calendar.Calendar client) throws IOException {

        String pageToken = null;
        do {
            CalendarList calendarList = client.calendarList().list().setPageToken(pageToken).execute();
            List<CalendarListEntry> items = calendarList.getItems();

            for (CalendarListEntry calendarListEntry : items) {
                System.out.println(calendarListEntry.getId());
            }
            pageToken = calendarList.getNextPageToken();
        } while (pageToken != null);
   }

   public void deleteCalendar(String accessToken, String googleCalendarId) {
       try {
           Credential credential =
               new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessToken);
           calendarClient = new com.google.api.services.calendar.Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).
               setApplicationName(APPLICATION_NAME).build();
           calendarClient.calendars().delete(googleCalendarId).execute();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}
