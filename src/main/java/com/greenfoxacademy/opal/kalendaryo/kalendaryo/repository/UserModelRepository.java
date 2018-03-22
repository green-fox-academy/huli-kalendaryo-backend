package com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserModelRepository extends CrudRepository<UserModel, Long> {

    UserModel findByClientToken(String clientToken);
    UserModel findByUserEmail(String email);
    UserModel findUserModelByAuthModelListIsContaining(AuthModel authModel);
}
