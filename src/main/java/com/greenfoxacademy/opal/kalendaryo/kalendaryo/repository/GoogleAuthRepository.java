package com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.GoogleAuth;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoogleAuthRepository extends CrudRepository<GoogleAuth, String> {

    List<GoogleAuth> findGoogleAuthByEmail(String email);

    GoogleAuth findByEmail (String email);

}
