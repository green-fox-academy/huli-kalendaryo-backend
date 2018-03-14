package com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository;


import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.KalUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KalUserRepository extends CrudRepository<KalUser, Long> {

    KalUser findById(Long id);
    KalUser findByClientToken(String clientToken);
    KalUser findByUserEmail(String email);
    KalUser findKalUserByAuthModelListIsContaining(AuthModel authModel);
}
