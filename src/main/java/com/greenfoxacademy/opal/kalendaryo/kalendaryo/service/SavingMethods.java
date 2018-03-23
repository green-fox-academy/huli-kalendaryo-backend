package com.greenfoxacademy.opal.kalendaryo.kalendaryo.service;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.GoogleAuth;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.Kalendar;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface SavingMethods {

    void saveGoogleAuth(GoogleAuth googleAuth) throws IOException;

    void saveKalendar(Kalendar kalendar);

}
