package com.example.HANDIPRO.adapter;


import com.example.HANDIPRO.Repositories.PatientRegistrationRepository;
import com.example.HANDIPRO.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SqlPatientRegistrationRepository extends PatientRegistrationRepository, JpaRepository<Patient,Integer> {
    /*@Override
    boolean existsByEmail(String email);

    @Override
    Optional<Patient> findByEmail(String email);*/
}
