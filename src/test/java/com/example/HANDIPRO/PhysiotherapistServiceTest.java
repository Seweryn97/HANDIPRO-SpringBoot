package com.example.HANDIPRO;

import com.example.HANDIPRO.Repositories.PhysiotherapistRegistrationRepository;
import com.example.HANDIPRO.exceptions.RecordAlreadyExistsException;
import com.example.HANDIPRO.exceptions.RecordNotFoundException;
import com.example.HANDIPRO.models.DTO.PhysiotherapistReadDTO;
import com.example.HANDIPRO.models.DTO.PhysiotherapistUpdateDTO;
import com.example.HANDIPRO.models.Patient;
import com.example.HANDIPRO.models.Physiotherapist;
import com.example.HANDIPRO.models.Task;
import com.example.HANDIPRO.services.PhysiotherapistService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

import org.mockito.Mockito;

import java.util.*;
import static org.mockito.Mockito.*;

public class PhysiotherapistServiceTest {


    @Test()
    public void register_physiotherapist_test() throws RecordAlreadyExistsException {

        //given
        var repository = mock(PhysiotherapistRegistrationRepository.class);
        var physiotherapistService = mock(PhysiotherapistService.class);

        Physiotherapist physiotherapist = new Physiotherapist();

        physiotherapist.setId(1);
        physiotherapist.setName("Imie");
        physiotherapist.setSurname("Nazwisko");
        physiotherapist.setEmail("email@gmail.com");
        physiotherapist.setPassword("Haslo1234");
        physiotherapist.setRepeatedpassword("Haslo1234");

        //when
        when(repository.existsByEmail(physiotherapist.getEmail())).thenReturn(true);
        doReturn(physiotherapist).when(physiotherapistService).
                registerPhysiotherapist(Mockito.any(Physiotherapist.class));
        Physiotherapist physiotherapist1 = physiotherapistService.registerPhysiotherapist(new Physiotherapist());

        //then
        Assert.assertEquals(physiotherapist1.getName(),"Imie");
        Assert.assertEquals(physiotherapist1.getSurname(),"Nazwisko");
        Assert.assertEquals(physiotherapist1.getEmail(), "email@gmail.com");
        Assert.assertEquals(physiotherapist1.getPassword(),"Haslo1234");
    }

    @Test
    public void get_physiotherapist_by_id_test() throws RecordNotFoundException {

        //given
        var physiotherapistService = mock(PhysiotherapistService.class);

        Physiotherapist physiotherapist = new Physiotherapist();

        physiotherapist.setId(1);
        physiotherapist.setName("Imie");
        physiotherapist.setSurname("Nazwisko");
        physiotherapist.setEmail("email@gmail.com");
        physiotherapist.setPassword("Haslo1234");
        physiotherapist.setRepeatedpassword("Haslo1234");

        //when
        doReturn(physiotherapist).when(physiotherapistService)
                .getPhysiotherapistById(Mockito.anyInt());
        Physiotherapist physiotherapist1 = physiotherapistService.getPhysiotherapistById(1);

        //then
        Assert.assertEquals(physiotherapist1.getName(),"Imie");
        Assert.assertEquals(physiotherapist1.getSurname(),"Nazwisko");
        Assert.assertEquals(physiotherapist1.getEmail(),"email@gmail.com");
        Assert.assertEquals(physiotherapist1.getPassword(),"Haslo1234");
    }

    @Test
    public void email_update_test(){

        //given
        var repository = mock(PhysiotherapistRegistrationRepository.class);
        PhysiotherapistService physiotherapistService = new PhysiotherapistService(repository);

        PhysiotherapistUpdateDTO physiotherapistUpdateDTO = new PhysiotherapistUpdateDTO();
        Physiotherapist physiotherapist = new Physiotherapist();

        physiotherapistUpdateDTO.setId(1);
        physiotherapistUpdateDTO.setEmail("email@gmail.com");
        physiotherapistUpdateDTO.setPassword("Haslo1234");
        physiotherapistUpdateDTO.setRepeatedpassword("Haslo1234");

        physiotherapist.setId(1);
        physiotherapist.setName("Imie");
        physiotherapist.setSurname("Nazwisko");
        physiotherapist.setEmail("email2@gmail.com");
        physiotherapist.setPassword("Haslo1234");
        physiotherapist.setRepeatedpassword("Haslo1234");

        //then
        Assert.assertTrue(physiotherapistService.emailUpdate(physiotherapistUpdateDTO,physiotherapist));
    }

    @Test
    public void password_update_test(){

        //given
        var repository = mock(PhysiotherapistRegistrationRepository.class);
        PhysiotherapistService physiotherapistService = new PhysiotherapistService(repository);

        PhysiotherapistUpdateDTO physiotherapistUpdateDTO = new PhysiotherapistUpdateDTO();
        Physiotherapist physiotherapist = new Physiotherapist();

        physiotherapistUpdateDTO.setId(1);
        physiotherapistUpdateDTO.setEmail("email@gmail.com");
        physiotherapistUpdateDTO.setPassword("Haslo1234");
        physiotherapistUpdateDTO.setRepeatedpassword("Haslo1234");

        physiotherapist.setId(1);
        physiotherapist.setName("Imie");
        physiotherapist.setSurname("Nazwisko");
        physiotherapist.setEmail("email2@gmail.com");
        physiotherapist.setPassword("Haslo1234");
        physiotherapist.setRepeatedpassword("Haslo1234");

        //then
        Assert.assertFalse(physiotherapistService.passwordUpdate(physiotherapistUpdateDTO,physiotherapist));

    }

    public List<PhysiotherapistReadDTO> prepare_mockito_physiotherapist(){

        List<PhysiotherapistReadDTO> physiotherapistReadDTO= new LinkedList<>();
        Set<Patient> patients = new HashSet<>();
        Set<Task> tasks = new HashSet<>();

        Physiotherapist physiotherapist = new Physiotherapist();
        Physiotherapist physiotherapist1 = new Physiotherapist();
        Task task = new Task();
        Patient patient = new Patient();
        tasks.add(task);
        patient.setTasks(tasks);
        patients.add(patient);

        physiotherapist.setId(1);
        physiotherapist.setName("Imie");
        physiotherapist.setSurname("Nazwisko");
        physiotherapist.setEmail("emial@gmial.com");
        physiotherapist.setPassword("Haslo1234");
        physiotherapist.setRepeatedpassword("Haslo1234");
        physiotherapist.setPatients(patients);

        physiotherapist1.setId(2);
        physiotherapist1.setName("Imie2");
        physiotherapist1.setSurname("Nazwisko2");
        physiotherapist1.setEmail("emial2@gmial.com");
        physiotherapist1.setPassword("Haslo12342");
        physiotherapist1.setRepeatedpassword("Haslo12342");
        physiotherapist1.setPatients(patients);

        physiotherapistReadDTO.add(new PhysiotherapistReadDTO(physiotherapist));
        physiotherapistReadDTO.add(new PhysiotherapistReadDTO(physiotherapist1));

        return physiotherapistReadDTO;

    }

    @Test
    public void read_physiotherapist_test() {

        //given
        var physiotherapistService = mock(PhysiotherapistService.class);

        //when
        when(physiotherapistService.readPhysiotherapist()).thenReturn(prepare_mockito_physiotherapist());
        List<PhysiotherapistReadDTO> physiotherapistReadDTOS = physiotherapistService.readPhysiotherapist();

        //then
        Assert.assertThat(physiotherapistReadDTOS, Matchers.hasSize(2));
    }


    @Test
    public void is_patient_password_format_ok_test(){
        //given
        var repository = mock(PhysiotherapistRegistrationRepository.class);
        PhysiotherapistService physiotherapistService = new PhysiotherapistService(repository);

        //then
        Assertions.assertTrue(physiotherapistService.isPasswordFormatOk("Haselkoooo1"));
        Assertions.assertFalse(physiotherapistService.isPasswordFormatOk("111"));
        Assertions.assertTrue(physiotherapistService.isPasswordFormatOk("DobryDzien1234"));
    }
}

