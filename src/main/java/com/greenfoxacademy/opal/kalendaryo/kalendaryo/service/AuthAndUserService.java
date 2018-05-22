package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.google.api.client.util.Base64;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.exception.ValidationException;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.UserResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.api.authresponses.PostAuthResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.GoogleAuth;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.KalUser;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.KalUserRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.authorization.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;

@Service
public class AuthAndUserService {

  @Autowired
  GoogleAuthRepository googleAuthRepository;

  @Autowired
  KalUserRepository kalUserRepository;

  @Autowired
  Authorization authorization;

  public void saveKalUser(KalUser kalUser) {
    if (kalUserRepository.findByUserEmail(kalUser.getUserEmail()) != null) {
      kalUser = kalUserRepository.findByUserEmail(kalUser.getUserEmail());
    }
    kalUserRepository.save(kalUser);
  }

  public void addUserToGoogleAuth(GoogleAuth googleAuth, KalUser kalUser) throws IOException {
    googleAuth.setUser(kalUser);
    saveGoogleAuth(googleAuth);
  }

  private KalUser findUserByClientToken(String clientToken){
      return kalUserRepository.findByClientToken(clientToken);
  }

  private KalUser findUserByUserMail(String email) {
    return kalUserRepository.findByUserEmail(email);
  }

  public KalUser findUserByAuth(GoogleAuth googleAuth) {
    return kalUserRepository.findKalUserByGoogleAuthListIsContaining(googleAuth);
  }

  public String getRandomClientToken() {
    SecureRandom secureRandom = new SecureRandom();
    byte[] random = new byte[20];
    secureRandom.nextBytes(random);
    return Base64.encodeBase64String(random);
  }

  public boolean checkIfEmailIsLoggedInUser(String email, String clientToken) throws ValidationException {
    try {
      KalUser user = findUserByClientToken(clientToken);
      return user.getUserEmail().equals(email);
    } catch (NullPointerException e) {
      throw new ValidationException("Client token is missing or invalid");
    }
  }

  private void saveGoogleAuth(GoogleAuth googleAuth) throws IOException {
    String accessToken = authorization.authorize(googleAuth.getAuthCode());
    googleAuth.setAccessToken(accessToken);
    googleAuthRepository.save(googleAuth);
  }

  public void deleteGoogleAuth(String email, String clientToken) throws ValidationException {
    try {
      if (!checkIfEmailIsLoggedInUser(email, clientToken)) {
        KalUser user = findUserByClientToken(clientToken);
        GoogleAuth googleAuth = googleAuthRepository.findByEmailAndUser_Id(email, user.getId());
        googleAuthRepository.delete(googleAuth);
      } else {
        throw new ValidationException("Primary email can not be deleted!");
      }
    } catch (NullPointerException e) {
      throw new ValidationException("Client token/email is missing or invalid");
    }

  }


  private List<GoogleAuth> listAllGoogleAccounts(KalUser user) {
    return googleAuthRepository.findAllByUser(user);
  }

  public UserResponse createUserResponseForGetAccounts(String clientToken) throws ValidationException {
    try {
      KalUser user = findUserByClientToken(clientToken);
      List<GoogleAuth> googleAuthList = listAllGoogleAccounts(findUserByClientToken(clientToken));
      return new UserResponse(user.getId(), user.getUserEmail(), googleAuthList);
    } catch (Exception e) {
      throw new ValidationException("Client token is missing or invalid");
    }

  }

  public PostAuthResponse createPostAuthResponse(String clientToken, GoogleAuth googleAuth) throws ValidationException {
    try {
      KalUser kalUser = setKalUserForPostAuth(clientToken, googleAuth);
      googleAuth = manageGoogleAuthForPostAuth(kalUser, googleAuth);
      return new PostAuthResponse(kalUser.getId(), kalUser.getClientToken(), googleAuth.getEmail(), googleAuth.getAccessToken());
    } catch (Exception e) {
      throw new ValidationException("Client token is missing or invalid");
    }
  }

  private KalUser setKalUserForPostAuth(String clientToken, GoogleAuth googleAuth) throws ValidationException {
    KalUser kalUser;
    if (!clientToken.equals("")) {
      kalUser = findUserByClientToken(clientToken);
    } else if (findUserByUserMail(googleAuth.getEmail()) != null) {
      kalUser = findUserByUserMail(googleAuth.getEmail());
    } else {
      kalUser = new KalUser(getRandomClientToken());
      kalUser.setUserEmail(googleAuth.getEmail());
    }
    return kalUser;
  }

  private GoogleAuth manageGoogleAuthForPostAuth(KalUser kalUser, GoogleAuth googleAuth) throws Exception {
    googleAuth.setUser(kalUser);
    if (checkIfGoogleAuthExist(googleAuth, kalUser.getId())) {
      googleAuth = googleAuthRepository.findByEmailAndUser_Id(googleAuth.getEmail(), kalUser.getId());
      return googleAuth;
    }
    saveGoogleAuth(googleAuth);
    return googleAuth;
  }

  private boolean checkIfGoogleAuthExist(GoogleAuth googleAuth, Long kalUserId) {
    return googleAuthRepository.findByEmailAndUser_Id(googleAuth.getEmail(), kalUserId) != null;
  }
}
