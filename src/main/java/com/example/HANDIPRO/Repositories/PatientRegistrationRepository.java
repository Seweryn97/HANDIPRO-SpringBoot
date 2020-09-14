package com.example.HANDIPRO.Repositories;

import com.example.HANDIPRO.models.DTO.PatientReadDTO;
import com.example.HANDIPRO.models.Patient;
import java.util.List;
import java.util.Optional;

public interface PatientRegistrationRepository {

    List<Patient> findAll();

    Patient save(Patient Entity);

    Optional<Patient> findByEmail(String email);

    boolean existsById (int id);

    Optional<Patient> findById(int id);

    boolean existsByEmail (String email);

    void deleteById(Integer id);


}
