package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.authorization;


import java.io.IOException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;

@Service
public interface Authorization {
    String authorize(String authCode) throws IOException;
}
