package com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository;


import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface UserModelRepository extends CrudRepository<UserModel, Long> {

    UserModel findById(Long userId);

    UserModel findByClientToken(String clientToken);
}

