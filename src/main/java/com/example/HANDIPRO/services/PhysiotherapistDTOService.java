package com.example.HANDIPRO.services;

import com.example.HANDIPRO.Repositories.PhysiotherapistRegistrationRepository;
import com.example.HANDIPRO.models.DTO.PhysiotherapistReadDTO;
import com.example.HANDIPRO.models.Physiotherapist;
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

    public PhysiotherapistReadDTO readPhysiotherapist(Physiotherapist physiotherapist){
        Physiotherapist result = repository.save(physiotherapist);
        return new PhysiotherapistReadDTO(result);
    }
}
