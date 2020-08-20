package com.example.HANDIPRO.controller;

import com.example.HANDIPRO.Repositories.PatientRegistrationRepository;
import com.example.HANDIPRO.Repositories.PhysiotherapistRegistrationRepository;
import com.example.HANDIPRO.models.Patient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class PatientRegistartionController {

    private final PatientRegistrationRepository patientRegistrationRepository;

    public PatientRegistartionController(PatientRegistrationRepository patientRegistrationRepository,
                                         PhysiotherapistRegistrationRepository physiotherapistRegistrationRepository) {
        this.patientRegistrationRepository = patientRegistrationRepository;
    }

    @GetMapping(path ="/register/patient" , params ={"!sort","!page","!size"})
    ResponseEntity<List<Patient>> readAllRegister(){
        return ResponseEntity.ok(patientRegistrationRepository.findAll());
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
}
