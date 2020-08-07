package com.example.HANDIPRO.controller;


import com.example.HANDIPRO.model.Registration;
import com.example.HANDIPRO.model.RegistrationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class RegistrationController {

    private final RegistrationRepository registerRepository;

    public RegistrationController(RegistrationRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    @GetMapping(path ="/register" , params ={"!sort","!page","!size"})
    ResponseEntity<List<Registration>> readAllRegister(){
        return ResponseEntity.ok(registerRepository.findAll());
    }

    @PostMapping(path = "/register")
    ResponseEntity<Registration> createRegister(@RequestBody Registration registerEntity){
        Registration result = registerRepository.save(registerEntity);

        return ResponseEntity.created(URI.create("/"+result.getId())).body(result);

    }


}
