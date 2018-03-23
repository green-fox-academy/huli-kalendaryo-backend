package com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.GoogleAuth;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoogleAuthRepository extends CrudRepository<GoogleAuth, String> {

<<<<<<< HEAD:src/main/java/com/greenfoxacademy/opal/kalendaryo/kalendaryo/repository/GoogleAuthRepository.java
    GoogleAuth findByEmail(String email);
=======
    AuthModel findByEmail (String email);
    AuthModel findByAuthCode (String authCode);
>>>>>>> 6f25c3cabe346ba5c96555ea88fd5ca39f9b585e:src/main/java/com/greenfoxacademy/opal/kalendaryo/kalendaryo/repository/AuthModelRepository.java
}
