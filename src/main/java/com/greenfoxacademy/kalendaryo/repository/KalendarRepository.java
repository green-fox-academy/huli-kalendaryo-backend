package com.greenfoxacademy.kalendaryo.repository;

import com.greenfoxacademy.kalendaryo.model.entity.KalUser;
import com.greenfoxacademy.kalendaryo.model.entity.Kalendar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KalendarRepository extends CrudRepository<Kalendar,Long> {

    List<Kalendar> findKalendarsByUser(KalUser kalUser);
    void deleteKalendarById(long id);
    Kalendar findKalendarById(long id);

}
