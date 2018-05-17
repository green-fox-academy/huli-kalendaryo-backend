package com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.GoogleAuth;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.KalUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoogleAuthRepository extends CrudRepository<GoogleAuth, String> {

    GoogleAuth findByEmail(String email);
    GoogleAuth findByEmailAndUser_Id(String email, Long Id);
    List<GoogleAuth> findByUser_Id(Long id);
}
