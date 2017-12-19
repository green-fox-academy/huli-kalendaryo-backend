package com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.UserModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthModelRepository {

    List<AuthModel> findAuthModelByEmail(String email);

    AuthModel findByEmail (String email);

    UserModel findAuthModelByUser(UserModel userModel);

}
