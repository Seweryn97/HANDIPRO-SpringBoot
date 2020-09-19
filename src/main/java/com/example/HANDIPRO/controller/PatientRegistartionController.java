package com.example.HANDIPRO.controller;

import com.example.HANDIPRO.Repositories.PatientRegistrationRepository;
import com.example.HANDIPRO.Repositories.PatientTokenRepository;
import com.example.HANDIPRO.exceptions.RecordAlreadyExistsException;
import com.example.HANDIPRO.exceptions.RecordNotFoundException;
import com.example.HANDIPRO.models.DTO.*;
import com.example.HANDIPRO.models.Patient;
import com.example.HANDIPRO.models.PatientToken;
import com.example.HANDIPRO.models.Physiotherapist;
import com.example.HANDIPRO.models.PhysiotherapistToken;
import com.example.HANDIPRO.services.PatientService;
import com.example.HANDIPRO.services.VerifyEmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class PatientRegistartionController {

    private final PatientRegistrationRepository patientRegistrationRepository;

    @Autowired
    PatientService patientService;

    @Autowired
    PatientTokenRepository patientTokenRepository;

    @Autowired
    VerifyEmailSender verifyEmailSender;

    public PatientRegistartionController(PatientRegistrationRepository patientRegistrationRepository) {
        this.patientRegistrationRepository = patientRegistrationRepository;
    }

    @GetMapping(path ="/patient/registration" , params ={"!sort","!page","!size"})
    ResponseEntity<List<PatientReadDTO>> readAllRegister(){

        return ResponseEntity.ok(patientService.readPatient());
    }

    @PostMapping("/patient/registration")
    ModelAndView registerPhysiotherapist(ModelAndView modelAndView, @RequestBody Patient patient) {

        try{

            patientService.registerPatient(patient);
            PatientToken patientToken = new PatientToken(patient);
            patientTokenRepository.save(patientToken);

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(patient.getEmail());
            simpleMailMessage.setSubject("Confirm your email address");
            simpleMailMessage.setFrom("handipro1234@gmail.com");
            simpleMailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8080/patient/confirm-email?token="+patientToken.getConfirmationtoken());

            verifyEmailSender.sendEmail(simpleMailMessage);

            modelAndView.addObject("email", patient.getEmail());

            modelAndView.setViewName("successfulRegisteration");

        }catch (RecordAlreadyExistsException ex){
            ex.getStackTrace();
            modelAndView.addObject("message","Email already exists");
            modelAndView.setViewName("error");
        }

        return modelAndView;

    }

    @GetMapping("/patient/confirm-email")
    ModelAndView confirmEmailAddress(ModelAndView modelAndView, @RequestParam("token") String confirmationToken){

        PatientToken token = patientTokenRepository.findByConfirmationtoken(confirmationToken);

        if(token != null){
            Patient patient = patientRegistrationRepository.
                    findByEmail(token.getPatient().getEmail());
            patient.setConfirmedemail(true);
            patientRegistrationRepository.save(patient);
            modelAndView.setViewName("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;

    }

    @PatchMapping("/patient/update/{data}/{id}")
    ResponseEntity<?>updatePatient(@RequestBody @Valid PatientUpdateDTO patient
            , @PathVariable int id, @PathVariable String data) throws RecordNotFoundException {
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

    @DeleteMapping("/patient/delete/{id}")
    ResponseEntity<String> removePatient(@PathVariable int id) throws RecordNotFoundException {
        return ResponseEntity.ok(patientService.deletePatient(id));
    }
}
