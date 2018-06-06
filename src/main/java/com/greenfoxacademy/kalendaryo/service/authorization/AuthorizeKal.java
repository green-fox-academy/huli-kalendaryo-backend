package com.greenfoxacademy.kalendaryo.service.authorization;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleRefreshTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.model.*;
import com.google.common.collect.Lists;
import com.greenfoxacademy.kalendaryo.model.api.GoogleCalendarFromAndroid;
import com.greenfoxacademy.kalendaryo.model.entity.GoogleAuth;
import com.greenfoxacademy.kalendaryo.model.entity.KalUser;
import com.greenfoxacademy.kalendaryo.model.entity.Kalendar;
import com.greenfoxacademy.kalendaryo.service.AuthAndUserService;
import com.greenfoxacademy.kalendaryo.model.api.KalendarFromAndroid;
import com.greenfoxacademy.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.kalendaryo.service.KalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Profile("dev")
public class AuthorizeKal implements Authorization{
    
    public static Integer FINAL_ATTEMPT = 2;
    private static final String APPLICATION_NAME = "Kalendaryo";
    private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"), ".credentials/calendar-java-quickstart");
    private static FileDataStoreFactory DATA_STORE_FACTORY;
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static HttpTransport HTTP_TRANSPORT;
    private com.google.api.services.calendar.Calendar mergedCalendarClient;
    private com.google.api.services.calendar.Calendar calendarFromAndroidClient;
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

    public TokenResponse authorize(String authCode) throws IOException {
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

        return tokenResponse;
    }

    public TokenResponse authorizeWithRefreshToken(String refreshToken) throws IOException {
        String clientId = System.getenv("CLIENT_ID");
        String clientSecret = System.getenv("CLIENT_SECRET");
        TokenResponse tokenResponse =
                new GoogleRefreshTokenRequest(
                        new NetHttpTransport(),
                        JSON_FACTORY,
                        refreshToken,
                        clientId,
                        clientSecret).execute();

        return tokenResponse;
    }

    public void createGoogleCalendarUnderAccount(KalendarFromAndroid kalendarFromAndroid, Kalendar kalendar, Integer attempt) throws IOException {
        try {
            String mergedCalendarId = kalendarFromAndroid.getOutputGoogleAuthId();
            GoogleCalendarFromAndroid[] sourceCalendarIds = kalendarFromAndroid.getInputGoogleCalendars();

            GoogleAuth googleAuth = googleAuthRepository.findByEmail(kalendarFromAndroid.getOutputGoogleAuthId());
            String accessToken = googleAuth.getAccessToken();

            buildMergedCalendarClient(mergedCalendarId);
            String destinationCalendarId = insertNewGoogleCalendar(kalendar.getName());

            kalendar.setGoogleCalendarId(destinationCalendarId);
            migrateEvents(sourceCalendarIds, destinationCalendarId);
            kalendarService.saveKalendar(kalendar);
        } catch (GoogleJsonResponseException e) {
            if (attempt != FINAL_ATTEMPT) {
                GoogleAuth googleAuth = googleAuthRepository.findByEmail(kalendarFromAndroid.getOutputGoogleAuthId());
                saveRefreshedAccessToken(googleAuth);
                createGoogleCalendarUnderAccount(kalendarFromAndroid, kalendar, attempt++);
            } else
                e.printStackTrace();
        }
    }

    private void migrateEvents(GoogleCalendarFromAndroid[] sourceCalendarIds , String googleCalendarId) throws IOException {
        for (int i = 0; i < sourceCalendarIds.length; i++) {
            buildCalendarFromAndroidClient(sourceCalendarIds[i].getEmail());
            String visibility = setVisibility(sourceCalendarIds[i]);
            String calendarId = sourceCalendarIds[i].getId();
            String pageToken = null;
            do {
                Events events = calendarFromAndroidClient.events().list(calendarId).setPageToken(pageToken).execute();
                List<Event> items = events.getItems();
                    for (Event event : items) {
                        event.setVisibility(visibility);
                        mergedCalendarClient.events().insert(googleCalendarId, event).execute();
                    }
                pageToken = events.getNextPageToken();
            } while (pageToken != null);
        }
    }

    private String insertNewGoogleCalendar(String name) throws IOException{
        Calendar calendar = new Calendar();
        calendar.setSummary(name);
        Calendar createdCalendar = mergedCalendarClient.calendars().insert(calendar).execute();
        return createdCalendar.getId();
    }

    private void buildCalendarFromAndroidClient(String email){
        String accessToken = googleAuthRepository.findByEmail(email).getAccessToken();
        Credential credential = createCredential(accessToken);
        calendarFromAndroidClient = new com.google.api.services.calendar.Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
          .setApplicationName(APPLICATION_NAME).build();
    }

    private void buildMergedCalendarClient(String mergedCalendarId) throws IOException{
        String accessToken = googleAuthRepository.findByEmail(mergedCalendarId).getAccessToken();
            Credential credential =
              new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessToken);
            mergedCalendarClient = new com.google.api.services.calendar.Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
              .setApplicationName(APPLICATION_NAME).build();
    }

    private String setVisibility(GoogleCalendarFromAndroid googleCalendarFromAndroid){
        return googleCalendarFromAndroid.getSharingOption();
    }

    public Credential createCredential(String accessToken){
        Credential credential =
          new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessToken);
        return credential;
    }

    public String saveRefreshedAccessToken (GoogleAuth googleAuth) throws IOException{
        String refreshToken = googleAuth.getRefreshToken();
        TokenResponse tokenResponse = authorizeWithRefreshToken(refreshToken);
        googleAuth.setAccessToken(tokenResponse.getAccessToken());
        googleAuthRepository.save(googleAuth);
        return tokenResponse.getAccessToken();
    }

   public void deleteCalendar(String accessToken, String googleCalendarId) throws IOException {
        Credential credential =
          new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessToken);
        mergedCalendarClient = new com.google.api.services.calendar.Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).
          setApplicationName(APPLICATION_NAME).build();
        mergedCalendarClient.calendars().delete(googleCalendarId).execute();
   }
}
