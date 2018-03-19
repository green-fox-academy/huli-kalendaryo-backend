package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.authorization.AuthorizeKal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Profile("dev")
public class SavingMethodsDevEnvImp implements Authorization {

    @Autowired
    AuthorizeKal authorizeKal;

    @Override
    public String authorize(String authCode) throws IOException {
        return AuthorizeKal.authorize(authCode);
    }
}
