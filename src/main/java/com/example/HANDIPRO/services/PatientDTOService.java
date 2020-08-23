package com.example.HANDIPRO.services;

import com.example.HANDIPRO.Repositories.PatientRegistrationRepository;
import com.example.HANDIPRO.models.DTO.PatientUpdateDTO;
import com.example.HANDIPRO.models.Patient;
import org.springframework.stereotype.Service;

@Service
public class PatientDTOService {
    private PatientRegistrationRepository repository;

    PatientDTOService(final PatientRegistrationRepository repository){
        this.repository = repository;
    }

    /*public PatientUpdateDTO createPatient (Patient patient){
        Patient result = repository.save(patient);
        return new PatientUpdateDTO(result);
    }*/
}
