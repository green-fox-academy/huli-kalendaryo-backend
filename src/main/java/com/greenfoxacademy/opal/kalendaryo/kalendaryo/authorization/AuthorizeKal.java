package com.greenfoxacademy.opal.kalendaryo.kalendaryo.authorization;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.*;
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
import com.google.api.services.calendar.CalendarScopes;
import com.google.common.collect.Lists;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.MergedCalendarFromAndroid;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.AuthModelRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.MergedCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.api.services.calendar.model.Calendar;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

@Service
public class AuthorizeKal {

    private static final String APPLICATION_NAME = "Kalendaryo";
    private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"), ".credentials/calendar-java-quickstart");
    private static FileDataStoreFactory DATA_STORE_FACTORY;
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static HttpTransport HTTP_TRANSPORT;
    private com.google.api.services.calendar.Calendar calendarClient;
    List<Calendar> addedCalendarUsingBatch = Lists.newArrayList();

    @Autowired
    AuthModelRepository authModelRepository;

    @Autowired
    MergedCalendarService mergedCalendarService;


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
    public static String authorize(String authCode) throws IOException {
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



    public  void createCalendar(MergedCalendarFromAndroid android) {
        try {
            String accessToken = authModelRepository.findByEmail(android.getOutputCalendarId()).getAccessToken();
            Credential credential =
                    new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessToken);
           // Credential credential = auth(android);
            System.out.println("credential: " + credential.toString());
            calendarClient = new com.google.api.services.calendar.Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
            String accesToken = "Bearer " + authModelRepository.findByEmail(android.getOutputCalendarId()).getAccessToken();
            addCalendarsUsingBatch(android);
           /* Calendar calendar = addCalendar(android);*/



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  /*  public  Calendar addCalendar(MergedCalendarFromAndroid android) throws IOException {//fromAndroid calendarlist as input param
        Calendar calendar1 = new com.google.api.services.calendar.model.Calendar();
        calendar1.setSummary(android.getOutputCalendarId()); // ezt ezzel setteled
        Calendar calendarResult = calendarClient.calendars().insert(calendar1).execute();
        return calendarResult;
    }*/

    public  Credential auth(MergedCalendarFromAndroid android) throws IOException {
        String authCode = "Bearer " + authModelRepository.findByEmail(android.getOutputCalendarId()).getAccessToken();
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                new InputStreamReader(AuthorizeKal.class.getResourceAsStream("/client_secrets.json")));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,clientSecrets,
                Collections.singleton(CalendarScopes.CALENDAR)).setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("offline").build();

        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize(authCode);
    }

    private void addCalendarsUsingBatch(MergedCalendarFromAndroid android) throws IOException {

        BatchRequest batch = calendarClient.batch();

        // Create the callback.
        JsonBatchCallback<Calendar> callback = new JsonBatchCallback<Calendar>() {

            @Override
            public void onSuccess(Calendar calendar, HttpHeaders responseHeaders) {
                addedCalendarUsingBatch.add(calendar);
            }

            @Override
            public void onFailure(GoogleJsonError e, HttpHeaders responseHeaders) {
                System.out.println("Error Message: " + e.getMessage());
            }
        };

        // Create 2 Calendar Entries to insert.
        String name= mergedCalendarService.inputCalendarSetter(android.getInputCalendarIds());
        Calendar entry1 = new Calendar().setSummary(name);
        calendarClient.calendars().insert(entry1).queue(batch, callback);



        batch.execute();
    }




}

