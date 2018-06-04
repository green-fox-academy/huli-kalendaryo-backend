package com.greenfoxacademy.kalendaryo.service;

import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.util.Base64;
import com.greenfoxacademy.kalendaryo.model.entity.GoogleAuth;
import com.greenfoxacademy.kalendaryo.model.entity.KalUser;
import com.greenfoxacademy.kalendaryo.exception.ValidationException;
import com.greenfoxacademy.kalendaryo.model.api.UserResponse;
import com.greenfoxacademy.kalendaryo.model.api.authresponses.PostAuthResponse;
import com.greenfoxacademy.kalendaryo.repository.GoogleAuthRepository;
import com.greenfoxacademy.kalendaryo.repository.KalUserRepository;
import com.greenfoxacademy.kalendaryo.service.authorization.Authorization;
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

  private KalUser findUserByClientToken(String clientToken) {
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

  private void saveGoogleAuth(GoogleAuth googleAuth) throws IOException {
    TokenResponse tokens = authorization.authorize(googleAuth.getAuthCode());
    String accessToken = tokens.getAccessToken();
    String refreshTOken = tokens.getRefreshToken();
    googleAuth.setAccessToken(accessToken);
    googleAuth.setRefreshToken(refreshTOken);
    googleAuthRepository.save(googleAuth);
  }

  public void deleteGoogleAuth(String email, String clientToken) throws ValidationException {
    try {
      if (email.equals("")) {
        throw new ValidationException("Email is missing");
      } else {
        KalUser user = findUserByClientToken(clientToken);
        if (!checkIfEmailIsLoggedInUser(email, user)) {
          GoogleAuth googleAuth = googleAuthRepository.findByUser_IdAndEmail(user.getId(), email);
          googleAuthRepository.delete(googleAuth);
        } else {
          throw new ValidationException("Primary email can not be deleted!");
        }
      }
    } catch (NullPointerException e) {
      throw new ValidationException("Client token is missing or invalid");
    }
  }

  public boolean checkIfEmailIsLoggedInUser(String email, KalUser kalUser) throws ValidationException {
    try {
      return kalUser.getUserEmail().equals(email);
    } catch (NullPointerException e) {
      throw new ValidationException("Client token is missing or invalid");
    }
  }

  public UserResponse createUserResponseForGetAccounts(String clientToken) throws ValidationException {
    try {
      KalUser user = findUserByClientToken(clientToken);
      List<GoogleAuth> googleAuthList = listAllGoogleAccounts(user);
      return new UserResponse(user.getId(), user.getUserEmail(), googleAuthList);
    } catch (NullPointerException e) {
      throw new ValidationException("Client token is missing or invalid");
    }
  }

  private List<GoogleAuth> listAllGoogleAccounts(KalUser user) {
    return googleAuthRepository.findAllByUser(user);
  }

  public PostAuthResponse createPostAuthResponse(String clientToken, GoogleAuth googleAuth) throws ValidationException {
    try {
      if (googleAuth.getEmail() == null) {
        throw new ValidationException("Email is missing");
      } else {
        KalUser kalUser = setKalUserForPostAuth(clientToken, googleAuth);
        googleAuth = manageGoogleAuthForPostAuth(kalUser, googleAuth);
        return new PostAuthResponse(kalUser.getId(), kalUser.getClientToken(), googleAuth.getEmail(), googleAuth.getAccessToken());
      }
    } catch (IOException e) {
      throw new ValidationException("Error while saving");
    }
  }

  public KalUser setKalUserForPostAuth(String clientToken, GoogleAuth googleAuth) {
    KalUser kalUser;
    if (clientTokenExist(clientToken)) {
      kalUser = findUserByClientToken(clientToken);
    } else if (userWithEmailIsExisting(googleAuth.getEmail())) {
      kalUser = findUserByUserMail(googleAuth.getEmail());
    } else {
      kalUser = new KalUser(getRandomClientToken());
      kalUser.setUserEmail(googleAuth.getEmail());
    }
    return kalUser;
  }

  public boolean clientTokenExist(String clientToken) {
    return findUserByClientToken(clientToken) != null;
  }

  public boolean userWithEmailIsExisting(String email) {
    return kalUserRepository.findByUserEmail(email) != null;
  }

  public GoogleAuth manageGoogleAuthForPostAuth(KalUser kalUser, GoogleAuth googleAuth) throws IOException {
    googleAuth.setUser(kalUser);
    if (checkIfGoogleAuthExist(googleAuth, kalUser.getId())) {
      return googleAuthRepository.findByUser_IdAndEmail(kalUser.getId(), googleAuth.getEmail());
    }
    saveGoogleAuth(googleAuth);
    return googleAuth;
  }

  public boolean checkIfGoogleAuthExist(GoogleAuth googleAuth, Long kalUserId) {
    return googleAuthRepository.findByUser_IdAndEmail(kalUserId, googleAuth.getEmail()) != null;
  }

  public String refreshAccessTokenForAndroid (String clientToken, String email) throws ValidationException{
    try {
      KalUser user = kalUserRepository.findByClientToken(clientToken);
      GoogleAuth googleAuth = googleAuthRepository.findByUser_IdAndEmail(user.getId(), email);
      TokenResponse tokenResponse = authorization.authorizeWithRefreshToken(googleAuth.getRefreshToken());
      googleAuth.setAccessToken(tokenResponse.getAccessToken());
      googleAuthRepository.save(googleAuth);
      return tokenResponse.getAccessToken();
    } catch (NullPointerException e) {
      throw new ValidationException("Client token is missing or invalid");
    } catch (IOException i) {
      throw new ValidationException("Error while refreshing the access token");
    }
  }
}
