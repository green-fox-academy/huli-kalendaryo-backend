package com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.AuthModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthModelRepository  extends CrudRepository<AuthModel, String> {

    List<AuthModel> findAuthModelByEmail(String email);
}
