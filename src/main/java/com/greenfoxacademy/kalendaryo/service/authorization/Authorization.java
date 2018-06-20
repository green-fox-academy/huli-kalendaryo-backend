package com.greenfoxacademy.kalendaryo.service.authorization;

import com.google.api.client.auth.oauth2.TokenResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface Authorization {

    TokenResponse authorize(String authCode) throws IOException;
    TokenResponse authorizeWithRefreshToken(String refreshToken) throws IOException;

}
