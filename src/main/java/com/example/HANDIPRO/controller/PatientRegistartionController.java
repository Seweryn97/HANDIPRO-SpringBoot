package com.example.HANDIPRO.controller;

import com.example.HANDIPRO.Repositories.PatientRegistrationRepository;
import com.example.HANDIPRO.models.DTO.PatientReadDTO;
import com.example.HANDIPRO.models.DTO.PatientUpdateDTO;
import com.example.HANDIPRO.models.Patient;
import com.example.HANDIPRO.services.PatientDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
public class PatientRegistartionController {

    private final PatientRegistrationRepository patientRegistrationRepository;

    @Autowired
    PatientDTOService patientDTOService;

    public PatientRegistartionController(PatientRegistrationRepository patientRegistrationRepository) {
        this.patientRegistrationRepository = patientRegistrationRepository;
    }

    @GetMapping(path ="/register/patient" , params ={"!sort","!page","!size"})
    ResponseEntity<List<PatientReadDTO>> readAllRegister(){
        List<Patient> patients = patientRegistrationRepository.findAll();
        List<PatientReadDTO> patientsDTO = new ArrayList<>();

        Iterator<Patient> iterator = patients.iterator();
        iterator.forEachRemaining(patient -> {
            patientsDTO.add(patientDTOService.readPatient(patient));
        });

        return ResponseEntity.ok(patientsDTO);
    }

    @PostMapping(path = "/register/patient")
    ResponseEntity<Patient> createRegister(@RequestBody @Valid  Patient patient ){
        if(!patientRegistrationRepository.existsByEmail(patient.getEmail())){

            Patient result = patientRegistrationRepository.save(patient);

            /*try {
                patientRegistrationRepository.sendMailNotification(registerEntity);
            }catch (MessagingException ex){
                ex.printStackTrace();
            }*/
            return ResponseEntity.created(URI.create("/"+result.getId())).body(result);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @PatchMapping(path = "/updatepatient")
    ResponseEntity<?>togglePatientWithPhysiotherapist(@RequestBody @Valid PatientUpdateDTO patientDTO){
        Optional<Patient> optionalPatient = patientRegistrationRepository.findById(patientDTO.getId());

        if(!optionalPatient.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Patient patient = optionalPatient.get();
        patientDTOService.updatePatientWithPhysiotherapist(patient,patientDTO);
        PatientReadDTO result = patientDTOService.readPatient(patient);
        return ResponseEntity.ok(result);
    }
}
