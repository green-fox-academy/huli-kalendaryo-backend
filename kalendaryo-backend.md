# Kalendaryo backend 

## General overview of the application
The spring backend part is connected to an SQL database and the Android frontend.
On the phone the app uses Google Authentication that is provided by the phone itself, so it will use the Google accounts stored in the phone by default. After the login the app gets the created Kalendars from the backend belonging to the User and displays them. 

The Kalendar is the newly created merged Google Calendar that contains the events from the different Google Calendars. On the Android frontend you can name your newly created Kalendar or you get a random GoT name as the Kalendar's name. You can set the visibility-options of the created Kalendar (public, private, default). The migration of the events form the selected Google Calendars are done the backend and the backedn itself creates the new Kalendar (Google Calendar) under the selected Google Account.

## Entity models
  * GoogleAuth - storing the authentications from Google
  
     Long id;  
     String email;  
     String authCode;  
     String displayName;  
     String accessToken;  
     String refreshToken;  
     List<GoogleCalendar> googleCalendars;  
     KalUser user;  
  * GoogleCalendar - Google Calendar object to create new Kalendars
  
     Long id;  
     String googleCalendarId;  
     Kalendar kalendar;  
     String visibility;  
  * Kalendar - the created merged Google Calendar from multiple GoogleCalendars
  
     Long id;  
     KalUser user;  
     String outputGoogleAuthId;  
     String name;  
     String googleCalendarId;  
     Date lastSync;  
     List<GoogleCalendar> googleCalendars;  
  * KalUser - our user in the backend with multiple Google authentications
  
     Long id;  
     String clientToken;  
     String userEmail;  
     List<Kalendar> kalendarList;  
     List<GoogleAuth> googleAuthList;  


## Controllers

 1. AccountController


     based on the provided clientToken from the frontend, returns the User with an ID, userEmail and a List<GoogleAuth>

```java
    @GetMapping(value = "/account")
        getGoogleAccounts (@RequestHeader("X-Client-Token") String clientToken)
```
    

    
    
    based on the clientToken and email form the frontend, deletes the GoogleAuth from the db

```java
    @DeleteMapping(value = "/account")
    	deleteGoogleAccount(@RequestHeader("X-Client-Token") String clientToken, 
                            @RequestHeader("email") String email)
```
    

    

 2. AuthController

    refreshes the AccessToken of the GoogleAccount and saves it to the db

```java
    @GetMapping("/auth")
        refreshAccessToken ((@RequestHeader("email") String email, 
                             @RequestHeader("X-Client-Token") String clientToken)
```
    
    

    
    authenticates the User based on the clientToken

```java
    @PostMapping("/auth")
        postauth (@RequestBody GoogleAuth googleAuth,  
                  @RequestHeader("X-Client-Token") String clientToken)
```
    
    
    
 3. KalendarController

     gets the Kalendars by the clientToken
     
```java
    @GetMapping(value = "/kalendar")
       getKalendarList(@RequestHeader("X-Client-Token") String clientToken)
```
    
    
    creates the new Kalendar based on the data from the frontend
    
```java
    @PostMapping(value = "/kalendar")
       postKalendar(@RequestHeader("X-Client-Token") String clientToken)
```
    
    
    deletes the Kalendar by it's ID
    
```java
    @DeleteMapping(value = "/kalendar/{id}")
       deleteKalendar(@RequestHeader("X-Client-Token") String clientToken,
                      @PathVariable(name = "id") long kalendarId)
```
    
    
    
## Services

    AuthorizeKal class
    it does everything related to the authorization with Google, with or without the refreshToken, also the new GoogleCalendar creation is here with migration and addition of the events from one GoogleCalendar to our created new Kalendar
    
    AuthAndUserService class
    the services used at the endpoints and other services can be found here
    
    GoogleCalendarService class
    saveGoogleCalendar and setGoogleCalendar methods
    
    KalendarService class
    the services related to the created merged Kalendar objects can be found here, for example the creation, the finding Kalendars by ID, User...
    
## ValidationException
    
    class that extends the Exception class and used as errorhandling and errormessages
    
## Database-migration
    
    FlyWay is used for database migration and "version control"
    
    
    
    
    
