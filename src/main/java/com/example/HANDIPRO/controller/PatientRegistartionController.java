package com.example.HANDIPRO.controller;

import com.example.HANDIPRO.Repositories.PatientRegistrationRepository;
import com.example.HANDIPRO.models.DTO.*;
import com.example.HANDIPRO.models.Patient;
import com.example.HANDIPRO.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/register/patient")
    ResponseEntity<Patient> createRegister(@RequestBody @Valid  Patient patient ){
        if(!patientRegistrationRepository.existsByEmail(patient.getEmail())
                && patientService.isPasswordFormatOk(patient.getPassword())){
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

    @PatchMapping("/update/patient/{data}/{id}")
    ResponseEntity<?>updatePatient(@RequestBody @Valid PatientUpdateDTO patient
            , @PathVariable int id, @PathVariable String data){
        boolean isPresent = false;
        if(data.equals("email")){
            isPresent = patientService.emailUpdate(patient,patientService.getPatientById(id));
        }
        if(data.equals("password")){
            isPresent = patientService.passwordUpdate(patient,patientService.getPatientById(id));
        }
        if(data.equals("physiotherapist")){
            isPresent = patientService.physiotherapistUpdate(patient,patientService.getPatientById(id));
        }
        if(isPresent){
            PatientReadDTO result = patientService.readPatient().get(id-1);

            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok(PatientService.message);
    }

    @DeleteMapping("/delete/patient/{id}")
    ResponseEntity<String> removePatient(@PathVariable int id){
        return ResponseEntity.ok(patientService.deletePatient(id));
    }
}
