package com.example.HANDIPRO.services;

import com.example.HANDIPRO.Repositories.PatientRegistrationRepository;
import com.example.HANDIPRO.models.DTO.PatientReadDTO;
import com.example.HANDIPRO.models.DTO.PatientUpdateDTO;
import com.example.HANDIPRO.models.Patient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private PatientRegistrationRepository repository;

    public PatientService(final PatientRegistrationRepository repository){
        this.repository = repository;
    }

    public boolean updatePatientWithPhysiotherapist (PatientUpdateDTO patientUpdateDTO){
        if(repository.findById(patientUpdateDTO.getId()).isEmpty()){
            throw new IllegalStateException("Patient is not found");
        }
        Optional<Patient> optionalPatient = repository.findById(patientUpdateDTO.getId());
        if(optionalPatient.isEmpty()){
            return false;
        }
        Patient patient = optionalPatient.get();

        patient.setPhysiotherapist(patientUpdateDTO.getPhysiotherapist());
        repository.save(patient);

        return true;
    }

    public List<PatientReadDTO> readPatient(){
        if(repository.findAll().isEmpty()){
            throw new IllegalStateException("Repository is empty");
        }

        List<Patient> patients = repository.findAll();
        List<PatientReadDTO> patientsDTO = new ArrayList<>();

        Iterator<Patient> iterator = patients.iterator();
        iterator.forEachRemaining(patient -> {
            patientsDTO.add(new PatientReadDTO(repository.save(patient)));
        });

        return  patientsDTO;
    }

    public boolean isPasswordFormatOk(Patient patient){

        boolean hasAtLeastOneBigLetter = !patient.getPassword().equals(patient.getPassword().toLowerCase());
        boolean hasAtLeastOneInt = patient.getPassword().matches(".*\\d.*");
        boolean isLongerOrEqualThanEight = patient.getPassword().length() >= 8;

        return hasAtLeastOneBigLetter && hasAtLeastOneInt && isLongerOrEqualThanEight;

    }
}
