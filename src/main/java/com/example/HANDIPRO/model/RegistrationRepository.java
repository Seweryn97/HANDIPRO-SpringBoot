package com.example.HANDIPRO.model;

import java.util.List;
import java.util.Optional;


public interface RegistrationRepository {

    List<Registration> findAll();

    Optional<Registration> findById(Integer id);

    boolean existsById(Integer id);

    Registration save(Registration Entity);

    Registration findByEmail(String email);

}
