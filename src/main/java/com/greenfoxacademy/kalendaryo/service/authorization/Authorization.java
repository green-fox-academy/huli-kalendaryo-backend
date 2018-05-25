package com.greenfoxacademy.kalendaryo.service.authorization;

import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public interface Authorization {

    String authorize(String authCode) throws IOException;
}
