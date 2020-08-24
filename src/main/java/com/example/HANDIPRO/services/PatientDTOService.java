package com.example.HANDIPRO.services;

import com.example.HANDIPRO.Repositories.PatientRegistrationRepository;
import com.example.HANDIPRO.models.DTO.PatientReadDTO;
import com.example.HANDIPRO.models.DTO.PatientUpdateDTO;
import com.example.HANDIPRO.models.Patient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientDTOService {
    private PatientRegistrationRepository repository;

    PatientDTOService(final PatientRegistrationRepository repository){
        this.repository = repository;
    }

    public PatientUpdateDTO updatePatientWithPhysiotherapist (Patient patient,PatientUpdateDTO patientUpdateDTO){
        patient.setPhysiotherapist(patientUpdateDTO.getPhysiotherapist());
        repository.save(patient);
        return new PatientUpdateDTO();
    }

    public PatientReadDTO readPatient(Patient patient){
        Patient result = repository.save(patient);
        return  new PatientReadDTO(result);
    }
}
