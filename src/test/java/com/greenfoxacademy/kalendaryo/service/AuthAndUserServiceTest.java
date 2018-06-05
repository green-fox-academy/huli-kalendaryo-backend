package com.greenfoxacademy.kalendaryo.service;

import com.greenfoxacademy.kalendaryo.exception.ValidationException;
import com.greenfoxacademy.kalendaryo.model.entity.GoogleAuth;
import com.greenfoxacademy.kalendaryo.model.entity.KalUser;
import com.greenfoxacademy.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.kalendaryo.repository.KalUserRepository;
import com.greenfoxacademy.kalendaryo.service.authorization.Authorization;
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
    GoogleAuth googleAuth = new GoogleAuth("test@email.com", "testCode", "Test Elek", new KalUser(), "testToken");
    assertTrue("check if created", authAndUserService.createPostAuthResponse("", googleAuth) != null);
  }

  @Test(expected = ValidationException.class)
  public void createPostAuthResponse_noEmail() throws ValidationException{
    GoogleAuth googleAuth = new GoogleAuth();
    assertTrue("check if userId is added", authAndUserService.createPostAuthResponse("", googleAuth).getClientToken() != null);
  }

  @Test
  public void setKalUserForPostAuth_noToken(){
    GoogleAuth googleAuth = new GoogleAuth();
    assertTrue("googleAuth is returned", authAndUserService.setKalUserForPostAuth("", googleAuth) != null);
  }

  @Test
  public void manageGoogleAuthForPostAuth_testReturn() throws IOException{
    KalUser kalUser = new KalUser();
    GoogleAuth googleAuth = new GoogleAuth();
    assertTrue("googleAuth is returned", authAndUserService.manageGoogleAuthForPostAuth(kalUser, googleAuth) != null);
  }
}