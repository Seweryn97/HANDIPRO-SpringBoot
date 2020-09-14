package com.example.HANDIPRO.services;

import com.example.HANDIPRO.Repositories.PhysiotherapistRegistrationRepository;
import com.example.HANDIPRO.models.DTO.PhysiotherapistReadDTO;
import com.example.HANDIPRO.models.DTO.PhysiotherapistUpdateDTO;
import com.example.HANDIPRO.models.Physiotherapist;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PhysiotherapistService {

    private PhysiotherapistRegistrationRepository repository;

    public static String message;

    public PhysiotherapistService(PhysiotherapistRegistrationRepository repository){
        this.repository = repository;
    }

    /*public PhysiotherapistDTO createPhysiotherapist(Physiotherapist physiotherapist){
        Physiotherapist result = repository.save(physiotherapist);
        return new PhysiotherapistDTO(result);
    }*/

    public Physiotherapist getPatientById(int id){
        if(repository.findById(id).isEmpty()){
            throw new IllegalStateException("Patient is not found");
        }
        Optional<Physiotherapist> optionalPhysiotherapist = repository.findById(id);

        return optionalPhysiotherapist.orElseThrow(() -> new NullPointerException("Physiotherapist does not exist"));
    }

    public boolean emailUpdate (PhysiotherapistUpdateDTO physiotherapistDTO, Physiotherapist physiotherapist){

        if(physiotherapistDTO.getEmail().equals(physiotherapist.getEmail()) ||
                !physiotherapistDTO.getPassword().equals(physiotherapist.getPassword())){
            message = "New email cannot be the same as old and passwords has to be the same";
            return false;
        }
        physiotherapist.setEmail(physiotherapistDTO.getEmail());

        repository.save(physiotherapist);

        return true;
    }

    public boolean passwordUpdate(PhysiotherapistUpdateDTO physiotherapistDTO, Physiotherapist physiotherapist){

        if(physiotherapistDTO.getPassword().equals(physiotherapist.getPassword()) ||
                !isPasswordFormatOk(physiotherapistDTO.getPassword()) ||
                !physiotherapistDTO.getRepeatedpassword().equals(physiotherapistDTO.getPassword())){
            message = "Password should has one big letter, should longer or equal than 8 and should has numbers. " +
                    "Password an repeatedpassword has to be the same. New password cannot be the same as old";
            return false;
        }

        physiotherapist.setPassword(physiotherapistDTO.getPassword());
        physiotherapist.setRepeatedpassword(physiotherapistDTO.getRepeatedpassword());

        repository.save(physiotherapist);

        return true;
    }

    public List<PhysiotherapistReadDTO> readPhysiotherapist(){

        List<Physiotherapist> physiotherapists = repository.findAll();
        List<PhysiotherapistReadDTO> physiotherapistReadDTO= new ArrayList<>();

        Iterator<Physiotherapist> iterator = physiotherapists.iterator();
        iterator.forEachRemaining(physiotherapist -> {
            physiotherapistReadDTO.add(new PhysiotherapistReadDTO(repository.save(physiotherapist)));
        });

        return physiotherapistReadDTO;
    }

    public String deletePhysiotherapist(int id){

        repository.deleteById(id);

        if(repository.findById(id).isPresent()){
            return "Physiotherapist has not been deleted";
        }
        else return "Physiotherapist has been deleted ";
    }

    public boolean isPasswordFormatOk(String password){
        boolean hasAtLeastOneBigLetter = !password.equals(password.toLowerCase());
        boolean hasAtLeastOneInt = password.matches(".*\\d.*");
        boolean isLongerOrEqualThanEight = password.length() >= 8;

        return hasAtLeastOneBigLetter && hasAtLeastOneInt && isLongerOrEqualThanEight;

    }
}
