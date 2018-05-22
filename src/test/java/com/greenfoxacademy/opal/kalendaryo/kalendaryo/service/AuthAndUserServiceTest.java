package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.exception.ValidationException;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.GoogleAuth;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.KalUser;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalUserRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.authorization.Authorization;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.Assert.*;

public class AuthAndUserServiceTest {
  @Mock
  GoogleAuthRepository googleAuthRepository;

  @Mock
  KalUserRepository kalUserRepository;

  @Mock
  Authorization authorization;

  @InjectMocks
  AuthAndUserService authAndUserService;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test(expected = ValidationException.class)
  public void checkIfEmailIsLoggedInUser_emptyClient() throws ValidationException{
    String email = "test@email.com";
    KalUser kalUser = new KalUser();
    authAndUserService.checkIfEmailIsLoggedInUser(email, kalUser);
  }

  @Test
  public void checkIfEmailIsLoggedInUser_sameEmail() throws ValidationException{
    String email = "test@email.com";
    KalUser kalUser = new KalUser();
    kalUser.setUserEmail("test@email.com");
    assertTrue("Both email is the same", authAndUserService.checkIfEmailIsLoggedInUser(email, kalUser));
  }

  @Test(expected = ValidationException.class)
  public void deleteGoogleAuthcheckIfEmailIsLoggedInUser_emptyClient() throws ValidationException{
    String email = "test@email.com";
    String clientToken = "";
    authAndUserService.deleteGoogleAuth(email, clientToken);
  }

  @Test(expected = ValidationException.class)
  public void createUserResponseForGetAccounts_emptyClient() throws ValidationException{
    String clientToken = "";
    authAndUserService.createUserResponseForGetAccounts(clientToken);
  }

  @Test
  public void createPostAuthResponse_testIfResponseIsCreated() throws ValidationException{
    String email = "test@email.com";
    String authCode = "testCode";
    String displayName = "Test Elek";
    KalUser kalUser = new KalUser();
    String accesToken = "testToken";
    GoogleAuth googleAuth = new GoogleAuth(email, authCode, displayName, kalUser, accesToken);
    assertTrue("check if created", authAndUserService.createPostAuthResponse("", googleAuth) != null);
  }

  @Test
  public void createPostAuthResponse_testKalUserClientToken() throws ValidationException{
    String clientToken = "";
    GoogleAuth googleAuth = new GoogleAuth();
    assertTrue("check if userId is added", authAndUserService.createPostAuthResponse("", googleAuth).getClientToken() != null);
  }

  @Test
  public void manageGoogleAuthForPostAuth_testReturn() throws IOException{
    KalUser kalUser = new KalUser();
    GoogleAuth googleAuth = new GoogleAuth();
    assertTrue("googleAuth is returned", authAndUserService.manageGoogleAuthForPostAuth(kalUser, googleAuth) != null);
  }
}