package com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.AuthModel;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthModelRepository extends CrudRepository<AuthModel, String> {

    List<AuthModel> findAuthModelByEmail(String email);
    AuthModel findByEmail (String email);
}
