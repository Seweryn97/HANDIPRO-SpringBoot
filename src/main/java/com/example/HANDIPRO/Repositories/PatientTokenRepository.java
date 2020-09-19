package com.example.HANDIPRO.Repositories;

import com.example.HANDIPRO.models.PatientToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientTokenRepository extends JpaRepository<PatientToken,Integer> {
    PatientToken findByConfirmationtoken(String PatientToken);

    void deleteById(Integer id);

}
