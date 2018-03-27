package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.authorization;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.model.*;
import com.google.common.collect.Lists;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.KalendarFromAndroid;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.Kalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalendarRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.KalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
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
    KalendarRepository kalendarRepository;

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

    public void createGoogleCalendarUnderAccount(KalendarFromAndroid android, Kalendar kalendar) {
        try {
            String accessToken = googleAuthRepository.findByEmail(android.getOutputGoogleAuthId()).getAccessToken();
            Credential credential =
                    new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessToken);
            calendarClient = new com.google.api.services.calendar.Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
            addCalendars(kalendar);
            List<String> calendarIds = getGoogleCalendarsId(android,calendarClient);
            getInputCalendarsEvents(calendarIds ,calendarClient);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCalendars(Kalendar kalendar) throws IOException {
        BatchRequest batch = calendarClient.batch();
        JsonBatchCallback<Calendar> callback = new JsonBatchCallback<Calendar>() {

            @Override
            public void onSuccess(Calendar calendar, HttpHeaders responseHeaders) {
                addedCalendarUsingBatch.add(calendar);
                updateKalendar(kalendar, calendar);
            }

            @Override
            public void onFailure(GoogleJsonError e, HttpHeaders responseHeaders) {
                System.out.println("Error Message: " + e.getMessage());
            }
        };
        Calendar createdGoogleCalendar = new Calendar().setSummary(kalendar.getOutputCalendarId());
        calendarClient.calendars().insert(createdGoogleCalendar).queue(batch, callback);
        batch.execute();
    }

    public void updateKalendar(Kalendar kalendar, Calendar calendar) {
        Kalendar theUltimateKalendar = kalendarRepository.findById(kalendar.getId());
        theUltimateKalendar.setGoogleCalendarId(calendar.getId());
        kalendarService.saveKalendar(theUltimateKalendar);
    }

    public List<String> getGoogleCalendarsId(KalendarFromAndroid android, com.google.api.services.calendar.Calendar service) throws IOException {
        List<String> googleCalendarIds = new ArrayList<>();
        String pageToken = null;
        do {
            CalendarList calendarList = service.calendarList().list().setPageToken(pageToken).execute();
            List<CalendarListEntry> items = calendarList.getItems();

            for (int i = 0; i < android.getInputGoogleCalendars().length; i++) {
                for (int j = 0; j < items.size(); j++) {
                    if (android.getInputGoogleCalendars()[i].equals(items.get(j).getSummary())) {
                        googleCalendarIds.add(items.get(j).getId());
                    }
                }
            }
            pageToken = calendarList.getNextPageToken();
        } while (pageToken != null);

        return googleCalendarIds;
    }

    public void getInputCalendarsEvents(List<String> calendarIds, com.google.api.services.calendar.Calendar service) throws IOException {

        String pageToken = null;
        do {
            for (int i = 0; i <calendarIds.size() ; i++) {
                Events events = service.events().list(calendarIds.get(i)).setPageToken(pageToken).execute();
                List<Event> items = events.getItems();
                for (Event event : items) {
                    System.out.println(event.getSummary());
                }
                pageToken = events.getNextPageToken();
            }
        } while (pageToken != null);
    }
}