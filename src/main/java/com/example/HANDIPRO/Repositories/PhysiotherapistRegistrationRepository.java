package com.example.HANDIPRO.Repositories;

import com.example.HANDIPRO.models.Physiotherapist;

import java.util.List;
import java.util.Optional;

public interface PhysiotherapistRegistrationRepository {

    List<Physiotherapist> findAll();

    Physiotherapist save(Physiotherapist Entity);

    //Optional <Physiotherapist> findById (Integer id);

    Physiotherapist findById(int id);

    Optional<Physiotherapist> findByEmail(String email);

    boolean existsByEmail (String email);

}
