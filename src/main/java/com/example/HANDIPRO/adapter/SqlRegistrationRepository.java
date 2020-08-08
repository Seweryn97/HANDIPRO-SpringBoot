package com.example.HANDIPRO.adapter;


import com.example.HANDIPRO.model.Registration;
import com.example.HANDIPRO.model.RegistrationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlRegistrationRepository extends RegistrationRepository, JpaRepository<Registration,Integer> {

    @Override
    Registration findByEmail(String email);
}
