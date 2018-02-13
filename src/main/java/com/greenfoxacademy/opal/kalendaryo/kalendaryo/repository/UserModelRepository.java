package com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository;


import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface UserModelRepository extends CrudRepository<UserModel, Long> {

    UserModel findById(Long id);

    UserModel findByClientToken(String clienttoken);
}
