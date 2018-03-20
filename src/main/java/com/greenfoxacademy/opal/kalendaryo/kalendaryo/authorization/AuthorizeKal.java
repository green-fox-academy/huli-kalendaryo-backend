package com.greenfoxacademy.opal.kalendaryo.kalendaryo.authorization;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.MergedCalendarFromAndroid;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.AuthModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.api.services.calendar.model.Calendar;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

@Service
public class AuthorizeKal {

    private static final String APPLICATION_NAME = "Kalendaryo";
    private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"), ".credentials/calendar-java-quickstart");
    private static FileDataStoreFactory DATA_STORE_FACTORY;
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static HttpTransport HTTP_TRANSPORT;
    private com.google.api.services.calendar.Calendar calendarClient;
    Credential credential;

    @Autowired
    AuthModelRepository authModelRepository;


    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    public  HttpResponse executeGet(
            HttpTransport transport, JsonFactory jsonFactory, String accessToken, GenericUrl url)
            throws IOException {
         credential =
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

    public  Credential auth(MergedCalendarFromAndroid android) throws IOException {
        String accessToken = authModelRepository.findByEmail(android.getOutputCalendarId()).getAccessToken();
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(AuthorizeKal.class.getResourceAsStream("/client_secrets.json")));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets,
                Collections.singleton(CalendarScopes.CALENDAR)).setDataStoreFactory(DATA_STORE_FACTORY).build();

        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize(accessToken);
    }

    public  void createCalendar(MergedCalendarFromAndroid android) {
         //auth accesstoken
        try {
            credential = auth(android);
        } catch (IOException e) {
            e.printStackTrace();
        }
        calendarClient = new com.google.api.services.calendar.Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
        try {
            Calendar calendar = addCalendar(android);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  Calendar addCalendar(MergedCalendarFromAndroid android) throws IOException {//fromAndroid calendarlist as input param
        Calendar calendar1 = new com.google.api.services.calendar.model.Calendar();
        calendar1.setSummary(android.getOutputCalendarId()); // ezt ezzel setteled
        Calendar calendarResult = calendarClient.calendars().insert(calendar1).execute();
        return calendarResult;
    }
}

