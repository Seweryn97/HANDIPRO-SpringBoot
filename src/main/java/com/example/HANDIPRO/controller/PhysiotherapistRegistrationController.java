package com.example.HANDIPRO.controller;

import com.example.HANDIPRO.Repositories.PhysiotherapistTokenRepository;
import com.example.HANDIPRO.exceptions.RecordAlreadyExistsException;
import com.example.HANDIPRO.exceptions.RecordNotFoundException;
import com.example.HANDIPRO.models.DTO.PhysiotherapistReadDTO;
import com.example.HANDIPRO.models.DTO.PhysiotherapistUpdateDTO;
import com.example.HANDIPRO.models.Physiotherapist;
import com.example.HANDIPRO.Repositories.PhysiotherapistRegistrationRepository;
import com.example.HANDIPRO.models.PhysiotherapistToken;
import com.example.HANDIPRO.services.PhysiotherapistService;
import com.example.HANDIPRO.services.VerifyEmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PhysiotherapistRegistrationController {

    private final PhysiotherapistRegistrationRepository physioterapistRegistrationRepository;

    @Autowired
    private PhysiotherapistService physiotherapistService;

    @Autowired
    private VerifyEmailSender verifyEmailSender;

    @Autowired
    private PhysiotherapistTokenRepository physiotherapistTokenRepository;

    public PhysiotherapistRegistrationController(PhysiotherapistRegistrationRepository physioterapistRegistrationRepository) {
        this.physioterapistRegistrationRepository = physioterapistRegistrationRepository;
    }

    @GetMapping(path ="/physiotherapist/registration" , params ={"!sort","!page","!size"})
    ResponseEntity<List<PhysiotherapistReadDTO>> readAllRegister(){
        return ResponseEntity.ok(physiotherapistService.readPhysiotherapist());
    }

    @PostMapping("/physiotherapist/registration")
    ModelAndView registerPhysiotherapist(ModelAndView modelAndView, @RequestBody Physiotherapist physiotherapist) {

        try{

            physiotherapistService.registerPhysiotherapist(physiotherapist);
            PhysiotherapistToken physiotherapistToken = new PhysiotherapistToken(physiotherapist);
            physiotherapistTokenRepository.save(physiotherapistToken);

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(physiotherapist.getEmail());
            simpleMailMessage.setSubject("Confirm your email address");
            simpleMailMessage.setFrom("handipro1234@gmail.com");
            simpleMailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8080/physiotherapist/confirm-email?token="+physiotherapistToken.getConfirmationtoken());

            verifyEmailSender.sendEmail(simpleMailMessage);

            modelAndView.addObject("email", physiotherapist.getEmail());

            modelAndView.setViewName("successfulRegisteration");

        }catch (RecordAlreadyExistsException ex){
            ex.getStackTrace();
            modelAndView.addObject("message","Email already exists");
            modelAndView.setViewName("error");
        }

        return modelAndView;

    }

    @GetMapping("/physiotherapist/confirm-email")
    ModelAndView confirmEmailAddress(ModelAndView modelAndView, @RequestParam("token") String confirmationToken){

        PhysiotherapistToken token = physiotherapistTokenRepository.findByConfirmationtoken(confirmationToken);

        if(token != null){
            Physiotherapist physiotherapist = physioterapistRegistrationRepository.
                    findByEmail(token.getPhysiotherapist().getEmail());

            physiotherapist.setConfirmedemail(true);
            physioterapistRegistrationRepository.save(physiotherapist);
            modelAndView.setViewName("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;

    }

    @PatchMapping("/physiotherapist/update/{data}/{id}")
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

    @DeleteMapping("/physiotherapist/delete/{id}")
    ResponseEntity<String> deletePhysiotherapist(@PathVariable int id) throws RecordNotFoundException {
       return ResponseEntity.ok(physiotherapistService.deletePhysiotherapist(id));
    }
}

