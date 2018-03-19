package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;


import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface Authorization {

    String authorize(String authCode) throws IOException;

}
