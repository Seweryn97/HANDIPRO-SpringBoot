
package com.example.HANDIPRO.controller;


import com.example.HANDIPRO.model.Registration;
import com.example.HANDIPRO.model.RegistrationRepository;
import com.example.HANDIPRO.services.VerifyEmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class RegistrationController {

    private final RegistrationRepository registerRepository;

    @Autowired
    private VerifyEmailSender verifyEmailSender;

    public RegistrationController(RegistrationRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    @GetMapping(path ="/register" , params ={"!sort","!page","!size"})
    ResponseEntity<List<Registration>> readAllRegister(){
        return ResponseEntity.ok(registerRepository.findAll());
    }

    @PostMapping(path = "/register")
    ResponseEntity<Registration> createRegister(@RequestBody  Registration registerEntity){
        if(registerRepository.findByEmail(registerEntity.getEmail()) == null){
            Registration result = registerRepository.save(registerEntity);

            try {
                verifyEmailSender.sendMailNotification(registerEntity);
            }catch (MessagingException ex){
                ex.printStackTrace();
            }
            return ResponseEntity.created(URI.create("/"+result.getId())).body(result);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @GetMapping(path = "/register/verifyemail/{email}")
    void confirmEmail(@PathVariable String email){
        registerRepository.findByEmail(email).setConfirmedemail(true);
    }

}

