package com.example.HANDIPRO.controller;

import com.example.HANDIPRO.exceptions.RecordNotFoundException;
import com.example.HANDIPRO.models.DTO.PhysiotherapistReadDTO;
import com.example.HANDIPRO.models.DTO.PhysiotherapistUpdateDTO;
import com.example.HANDIPRO.models.Physiotherapist;
import com.example.HANDIPRO.Repositories.PhysiotherapistRegistrationRepository;
import com.example.HANDIPRO.services.PhysiotherapistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class PhysiotherapistRegistrationController {

    private final PhysiotherapistRegistrationRepository physioterapistRegistrationRepository;

    @Autowired
    private PhysiotherapistService physiotherapistService;

   /* @Autowired
    private VerifyEmailSender verifyEmailSender;*/

    public PhysiotherapistRegistrationController(PhysiotherapistRegistrationRepository physioterapistRegistrationRepository) {
        this.physioterapistRegistrationRepository = physioterapistRegistrationRepository;
    }

    @GetMapping(path ="/register/physiotherapist" , params ={"!sort","!page","!size"})
    ResponseEntity<List<PhysiotherapistReadDTO>> readAllRegister(){
        return ResponseEntity.ok(physiotherapistService.readPhysiotherapist());
    }

    @PostMapping("/register/physiotherapist")
    ResponseEntity<Physiotherapist> createRegister(@RequestBody @Valid Physiotherapist registerEntity){
        if(!physioterapistRegistrationRepository.existsByEmail(registerEntity.getEmail())){
            Physiotherapist result = physioterapistRegistrationRepository.save(registerEntity);
            //logger.warning("Physiotherapist is added");

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

    @PatchMapping("/update/physiotherapist/{data}/{id}")
    ResponseEntity<?> updatePhysiotherpaist(@RequestBody @Valid PhysiotherapistUpdateDTO physiotherapist,
                                            @PathVariable int id, @PathVariable String data) throws RecordNotFoundException {
        boolean isPresent = false;
        if(data.equals("email")){
            isPresent = physiotherapistService.emailUpdate(physiotherapist,
                    physiotherapistService.getPhysiotherapistById(id));
        }
        if(data.equals("password")){
            isPresent = physiotherapistService.passwordUpdate(physiotherapist,
                    physiotherapistService.getPhysiotherapistById(id));
        }
        if(isPresent){
            PhysiotherapistReadDTO result = physiotherapistService.readPhysiotherapist().get(id-1);

            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok(PhysiotherapistService.message);
    }

    @DeleteMapping("/delete/physiotherapist/{id}")
    ResponseEntity<String> deletePhysiotherapist(@PathVariable int id) throws RecordNotFoundException {
       return ResponseEntity.ok(physiotherapistService.deletePhysiotherapist(id));
    }

}

