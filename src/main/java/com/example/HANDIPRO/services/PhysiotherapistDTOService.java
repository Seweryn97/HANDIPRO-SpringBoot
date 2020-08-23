package com.example.HANDIPRO.services;

import com.example.HANDIPRO.Repositories.PhysiotherapistRegistrationRepository;
import org.springframework.stereotype.Service;

@Service
public class PhysiotherapistDTOService {

    private PhysiotherapistRegistrationRepository repository;

    public PhysiotherapistDTOService(PhysiotherapistRegistrationRepository repository){
        this.repository = repository;
    }

    /*public PhysiotherapistDTO createPhysiotherapist(Physiotherapist physiotherapist){
        Physiotherapist result = repository.save(physiotherapist);
        return new PhysiotherapistDTO(result);
    }*/
}
