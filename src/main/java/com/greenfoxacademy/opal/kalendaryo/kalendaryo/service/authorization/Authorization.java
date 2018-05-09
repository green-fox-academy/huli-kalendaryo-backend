package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.authorization;


import java.io.IOException;
import org.springframework.stereotype.Service;

@Service
public interface Authorization {
    String authorize(String authCode) throws IOException;
}
