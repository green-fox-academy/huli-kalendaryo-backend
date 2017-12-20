package com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.UserModel;
import org.flywaydb.core.api.migration.jdbc.JdbcMigration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface UserModelRepository extends JdbcMigration {

    @Query(value = "SELECT * FROM user_model WHERE (plate LIKE 'RB%')", nativeQuery = true)
    UserModel findById();
}
