package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Random;

@Component
public class MockTokenGenerator {

    Random random = new Random();
    char[] chars =  {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x', 'y',
            'z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public MockTokenGenerator() {
    }

    public String generateMockToken () {
        String token = "";
        for (int i = 0; i < 20 ; i++) {
            int  n = random.nextInt(62);
            token+= chars[n];
        }
        return token;
    }
}
