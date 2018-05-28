package com.greenfoxacademy.kalendaryo.repository;

import com.greenfoxacademy.kalendaryo.model.entity.GoogleAuth;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoogleAuthRepository extends CrudRepository<GoogleAuth, String> {

    GoogleAuth findByEmail(String email);
    GoogleAuth findByUser_Id(long id);
    GoogleAuth findByUser_IdAndEmail(long id, String email);
}
