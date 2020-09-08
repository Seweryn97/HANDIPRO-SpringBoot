package com.example.HANDIPRO.services;

import com.example.HANDIPRO.Repositories.PatientRegistrationRepository;
import com.example.HANDIPRO.models.DTO.PatientReadDTO;
import com.example.HANDIPRO.models.DTO.PatientUpdateDTO;
import com.example.HANDIPRO.models.Patient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {
    private PatientRegistrationRepository repository;

    public PatientService(final PatientRegistrationRepository repository){
        this.repository = repository;
    }

    public PatientUpdateDTO updatePatientWithPhysiotherapist (Patient patient,PatientUpdateDTO patientUpdateDTO){
        if(repository.findById(patientUpdateDTO.getId()).isEmpty()){
            throw new IllegalArgumentException("Patient is not found");
        }

        patient.setPhysiotherapist(patientUpdateDTO.getPhysiotherapist());
        repository.save(patient);

        return new PatientUpdateDTO();
    }

    public PatientReadDTO readPatient(Patient patient){
        if(repository.findAll().isEmpty()){
            throw new IllegalArgumentException("Repository is empty");
        }

        Patient result = repository.save(patient);

        return  new PatientReadDTO(result);
    }

    public boolean isPasswordFormatOk(Patient patient){

        boolean hasAtLeastOneBigLetter = !patient.getPassword().equals(patient.getPassword().toLowerCase());
        boolean hasAtLeastOneInt = patient.getPassword().matches(".*\\d.*");
        boolean isLongerOrEqualThanEight = patient.getPassword().length() >= 8;

        return hasAtLeastOneBigLetter && hasAtLeastOneInt && isLongerOrEqualThanEight;

    }
}
