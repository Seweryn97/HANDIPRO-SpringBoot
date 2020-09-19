package com.example.HANDIPRO.services;

import com.example.HANDIPRO.Repositories.PhysiotherapistRegistrationRepository;
import com.example.HANDIPRO.Repositories.PhysiotherapistTokenRepository;
import com.example.HANDIPRO.exceptions.RecordAlreadyExistsException;
import com.example.HANDIPRO.exceptions.RecordNotFoundException;
import com.example.HANDIPRO.models.DTO.PhysiotherapistReadDTO;
import com.example.HANDIPRO.models.DTO.PhysiotherapistUpdateDTO;
import com.example.HANDIPRO.models.Patient;
import com.example.HANDIPRO.models.Physiotherapist;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.mapping.MetadataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PhysiotherapistService {

    private final PhysiotherapistRegistrationRepository repository;

    private final PhysiotherapistTokenRepository physiotherapistTokenRepository;

    public static String message;

    public PhysiotherapistService(PhysiotherapistRegistrationRepository repository,
                                  PhysiotherapistTokenRepository physiotherapistTokenRepository){
        this.repository = repository;
        this.physiotherapistTokenRepository = physiotherapistTokenRepository;
    }

    public Physiotherapist registerPhysiotherapist(Physiotherapist physiotherapist) throws RecordAlreadyExistsException{
        if(repository.existsByEmail(physiotherapist.getEmail())){
            throw new RecordAlreadyExistsException("Physiotherapist");
        }
        return repository.save(physiotherapist);
    }

    public Physiotherapist getPhysiotherapistById(int id) throws RecordNotFoundException {

        if(repository.findById(id).isEmpty()){
            throw new RecordNotFoundException("Physiotherapist");
        }
        Optional<Physiotherapist> optionalPhysiotherapist = repository.findById(id);

        return optionalPhysiotherapist.orElseThrow(() -> new RecordNotFoundException("Physiotherapist"));
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
            message = "Password should has one big letter, should be longer or equal than 8 and should has numbers. " +
                    "Password and repeatedpassword has to be the same. New password cannot be the same as old";
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

    public String deletePhysiotherapist(int id) throws RecordNotFoundException {

        Physiotherapist result = repository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("Physiotherapist"));

        for (Patient patient: result.getPatients()) {
            patient.setPhysiotherapist(null);
        }

        result.setPatients(null);

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
