package com.example.HANDIPRO.controller;

import com.example.HANDIPRO.Repositories.PatientRegistrationRepository;
import com.example.HANDIPRO.models.DTO.PatientReadDTO;
import com.example.HANDIPRO.models.DTO.PatientUpdateDTO;
import com.example.HANDIPRO.models.Patient;
import com.example.HANDIPRO.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
public class PatientRegistartionController {

    private final PatientRegistrationRepository patientRegistrationRepository;

    @Autowired
    PatientService patientService;

    public PatientRegistartionController(PatientRegistrationRepository patientRegistrationRepository) {
        this.patientRegistrationRepository = patientRegistrationRepository;
    }

    @GetMapping(path ="/register/patient" , params ={"!sort","!page","!size"})
    ResponseEntity<List<PatientReadDTO>> readAllRegister(){

        return ResponseEntity.ok(patientService.readPatient());
    }

    @PostMapping(path = "/register/patient")
    ResponseEntity<Patient> createRegister(@RequestBody @Valid  Patient patient ){
        if(!patientRegistrationRepository.existsByEmail(patient.getEmail())
                && patientService.isPasswordFormatOk(patient)){
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
    ResponseEntity<?>updatePatientWithPhysiotherapist(@RequestBody @Valid PatientUpdateDTO patientDTO){

        boolean isEmpty = patientService.updatePatientWithPhysiotherapist(patientDTO);
        if(isEmpty){
            return ResponseEntity.notFound().build();
        }
        PatientReadDTO result = patientService.readPatient().get(patientDTO.getId());

        return ResponseEntity.ok(result);
    }
}
