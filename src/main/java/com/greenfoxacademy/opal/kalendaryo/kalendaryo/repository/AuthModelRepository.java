package com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.UserModel;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface AuthModelRepository extends CrudRepository<AuthModel, String> {

    List<AuthModel> findAuthModelByEmail(String email);

    AuthModel findByEmail (String email);

    UserModel findAuthModelByUser(UserModel userModel);

}
