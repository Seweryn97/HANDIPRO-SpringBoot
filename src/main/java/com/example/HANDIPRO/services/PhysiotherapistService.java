package com.example.HANDIPRO.services;

import com.example.HANDIPRO.Repositories.PhysiotherapistRegistrationRepository;
import com.example.HANDIPRO.models.DTO.PhysiotherapistReadDTO;
import com.example.HANDIPRO.models.Patient;
import com.example.HANDIPRO.models.Physiotherapist;
import org.springframework.stereotype.Service;

@Service
public class PhysiotherapistService {

    private PhysiotherapistRegistrationRepository repository;

    public PhysiotherapistService(PhysiotherapistRegistrationRepository repository){
        this.repository = repository;
    }

    /*public PhysiotherapistDTO createPhysiotherapist(Physiotherapist physiotherapist){
        Physiotherapist result = repository.save(physiotherapist);
        return new PhysiotherapistDTO(result);
    }*/

    public PhysiotherapistReadDTO readPhysiotherapist(Physiotherapist physiotherapist){
        if(repository.findAll().isEmpty()){
            throw new IllegalArgumentException("Repository is empty");
        }
        Physiotherapist result = repository.save(physiotherapist);
        return new PhysiotherapistReadDTO(result);
    }

    public boolean isPasswordFormatOk(Physiotherapist physiotherapist){
        boolean hasAtLeastOneBigLetter = !physiotherapist.getPassword().equals(physiotherapist.getPassword().toLowerCase());
        boolean hasAtLeastOneInt = physiotherapist.getPassword().matches(".*\\d.*");
        boolean isLongerOrEqualThanEight = physiotherapist.getPassword().length() >= 8;

        return hasAtLeastOneBigLetter && hasAtLeastOneInt && isLongerOrEqualThanEight;

    }
}
