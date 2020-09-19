package com.example.HANDIPRO.adapter;


import com.example.HANDIPRO.models.Physiotherapist;
import com.example.HANDIPRO.Repositories.PhysiotherapistRegistrationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SqlPhysiotherapistRegistrationRepository extends PhysiotherapistRegistrationRepository, JpaRepository<Physiotherapist,Integer> {

    /*@Override
    Optional<Physiotherapist> findByEmail(String email);

    @Override
    boolean existsByEmail(String email);*/


}
