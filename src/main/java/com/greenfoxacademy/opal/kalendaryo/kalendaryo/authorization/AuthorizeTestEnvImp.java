package com.greenfoxacademy.opal.kalendaryo.kalendaryo.authorization;

import com.google.api.client.util.Base64;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.authorization.Authorization;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.AuthModelRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.SecureRandom;

@Component
@Profile("test")
public class AuthorizeTestEnvImp implements Authorization {

    @Autowired
    AuthModelRepository authModelRepository;

    @Autowired
    UserModelRepository userModelRepository;


    public String getRandomToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] random = new byte[20];
        secureRandom.nextBytes(random);
        return Base64.encodeBase64String(random);
    }

    @Override
    public String authorize(String authCode) throws IOException {
        return getRandomToken();
    }
}
