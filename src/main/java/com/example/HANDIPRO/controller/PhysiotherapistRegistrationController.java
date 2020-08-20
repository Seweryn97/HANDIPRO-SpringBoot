
package com.example.HANDIPRO.controller;

import com.example.HANDIPRO.models.Physiotherapist;
import com.example.HANDIPRO.Repositories.PhysiotherapistRegistrationRepository;
import com.example.HANDIPRO.services.VerifyEmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class PhysiotherapistRegistrationController {

    private final PhysiotherapistRegistrationRepository physioterapistRegistrationRepository;

   /* @Autowired
    private VerifyEmailSender verifyEmailSender;*/

    public PhysiotherapistRegistrationController(PhysiotherapistRegistrationRepository physioterapistRegistrationRepository) {
        this.physioterapistRegistrationRepository = physioterapistRegistrationRepository;
    }

    @GetMapping(path ="/register/physiotherapist" , params ={"!sort","!page","!size"})
    ResponseEntity<List<Physiotherapist>> readAllRegister(){
        return ResponseEntity.ok(physioterapistRegistrationRepository.findAll());
    }

    @PostMapping(path = "/register/physiotherapist")
    ResponseEntity<Physiotherapist> createRegister(@RequestBody @Valid Physiotherapist registerEntity){
        if(!physioterapistRegistrationRepository.existsByEmail(registerEntity.getEmail())){
            Physiotherapist result = physioterapistRegistrationRepository.save(registerEntity);

           /* try {
                verifyEmailSender.sendMailNotification(registerEntity);
            }catch (MessagingException ex){
                ex.printStackTrace();
            }*/
            return ResponseEntity.created(URI.create("/"+result.getId())).body(result);
        }
        return ResponseEntity.notFound().build();
    }

    /*@Transactional
    @PatchMapping(path = "/register/verifyemail/{email}")
    public ResponseEntity <?> toggleRegister(@PathVariable String email){
        if(!registerRepository.existsByEmail(email)){
            //logger.warning("Task no exist");
            return ResponseEntity.notFound().build();
        }
        registerRepository.findByEmail(email).ifPresent(registration -> registration.
                setConfirmedemail(!registration.isConfirmedemail()));
        return ResponseEntity.noContent().build();
    }*/

}

