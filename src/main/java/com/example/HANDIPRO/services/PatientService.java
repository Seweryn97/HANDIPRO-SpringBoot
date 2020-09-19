package com.example.HANDIPRO.services;

import com.example.HANDIPRO.Repositories.PatientRegistrationRepository;
import com.example.HANDIPRO.Repositories.PatientTokenRepository;
import com.example.HANDIPRO.exceptions.RecordAlreadyExistsException;
import com.example.HANDIPRO.exceptions.RecordNotFoundException;
import com.example.HANDIPRO.models.DTO.*;
import com.example.HANDIPRO.models.Patient;
import com.example.HANDIPRO.models.Physiotherapist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRegistrationRepository repository;

    private final PatientTokenRepository patientTokenRepository;

    public static String message;

    public PatientService(final PatientRegistrationRepository repository, PatientTokenRepository patientTokenRepository){
        this.repository = repository;
        this.patientTokenRepository = patientTokenRepository;
    }

    public Patient registerPatient(Patient patient) throws RecordAlreadyExistsException {
        if(repository.existsByEmail(patient.getEmail())){
            throw new RecordAlreadyExistsException("Patient");
        }
        return repository.save(patient);
    }

    public Patient getPatientById(int id) throws RecordNotFoundException {

        Optional<Patient> optionalPatient = repository.findById(id);

        return optionalPatient.orElseThrow(() -> new RecordNotFoundException("Patient"));
    }

    public boolean emailUpdate (PatientUpdateDTO patientDTO, Patient patient){

        if(patientDTO.getEmail().equals(patient.getEmail()) ||
                !patientDTO.getPassword().equals(patient.getPassword())){
            message = "New email cannot be the same as old and passwords has to be the same";
            return false;
        }
        patient.setEmail(patientDTO.getEmail());
        repository.save(patient);

        return true;
    }

    public boolean passwordUpdate(PatientUpdateDTO patientDTO, Patient patient){

        if(patientDTO.getPassword().equals(patient.getPassword()) || !isPasswordFormatOk(patientDTO.getPassword()) ||
        !patientDTO.getRepeatedpassword().equals(patientDTO.getPassword())){
            message = "Password should has one big letter, should longer or equal than 8 and should has numbers. " +
                    "Password an repeatedpassword has to be the same. New password cannot be the same as old";
            return false;
        }

        patient.setPassword(patientDTO.getPassword());
        patient.setRepeatedpassword(patientDTO.getRepeatedpassword());

        repository.save(patient);

        return true;
    }

    public boolean physiotherapistUpdate(PatientUpdateDTO patientDTO, Patient patient){

        if(patientDTO.getPhysiotherapist().equals(patient.getPhysiotherapist())){
            message = "Physiotherapist are the same";
            return false;
        }
        patient.setPhysiotherapist(patientDTO.getPhysiotherapist());

        return true;
    }

    public List<PatientReadDTO> readPatient(){

        List<Patient> patients = repository.findAll();
        List<PatientReadDTO> patientsDTO = new ArrayList<>();

        Iterator<Patient> iterator = patients.iterator();
        iterator.forEachRemaining(patient -> {
            patientsDTO.add(new PatientReadDTO(repository.save(patient)));
        });

        return  patientsDTO;
    }

    public String deletePatient(int id) throws RecordNotFoundException {

        repository.findById(id).orElseThrow(()-> new RecordNotFoundException("Patient"));

        repository.deleteById(id);

        if(repository.findById(id).isPresent()){
            return "Patient has not been deleted";
        }

        else return "Patient has been deleted ";
    }

    public boolean isPasswordFormatOk(String password){

        boolean hasAtLeastOneBigLetter = !password.equals(password.toLowerCase());
        boolean hasAtLeastOneInt = password.matches(".*\\d.*");
        boolean isLongerOrEqualThanEight = password.length() >= 8;

        return hasAtLeastOneBigLetter && hasAtLeastOneInt && isLongerOrEqualThanEight;

    }
}
