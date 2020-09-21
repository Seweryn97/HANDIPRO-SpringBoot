package com.example.HANDIPRO;

import com.example.HANDIPRO.Repositories.PatientRegistrationRepository;
import com.example.HANDIPRO.models.DTO.PatientUpdateDTO;
import com.example.HANDIPRO.models.Patient;
import com.example.HANDIPRO.services.PatientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PatientServiceTest {

    @Test
    public void isPatientNotExists(){
        /*var mockPatientRepository = mock(PatientRegistrationRepository.class);
        when(mockPatientRepository.findById(anyInt())).thenReturn(Optional.empty());

        var toTest = new PatientService(mockPatientRepository);

        var exception = catchThrowable(() -> {
            toTest.getPatientById(1);
        });

        assertThat(exception).isInstanceOf(NullPointerException.class);*/
    }

    @Test
    public void isEmailNotUpdatedTest(){
        /*var mockPatientRepository = mock(PatientRegistrationRepository.class);

        Patient patient = new Patient();
        PatientUpdateDTO patientUpdateDTO = new PatientUpdateDTO();

        var toTest = new PatientService(mockPatientRepository);

        patient.setEmail("email@gmail.com");
        patientUpdateDTO.setEmail("email@gmail.com");

        patient.setPassword("Password123");
        patientUpdateDTO.setPassword("password123");

        Assertions.assertFalse(toTest.emailUpdate(patientUpdateDTO,patient));*/

    }

    @Test
    public void isEmailUpdatedTest(){
        /*var mockPatientRepository = mock(PatientRegistrationRepository.class);

        Patient patient = new Patient();
        PatientUpdateDTO patientUpdateDTO = new PatientUpdateDTO();

        var toTest = new PatientService(mockPatientRepository);

        patient.setEmail("email@gmail.com");
        patientUpdateDTO.setEmail("email2@gmail.com");

        patient.setPassword("Password123");
        patientUpdateDTO.setPassword("Password123");

        Assertions.assertTrue(toTest.emailUpdate(patientUpdateDTO,patient));*/
    }

    @Test
    public void isPasswordNotUpdatedTest(){
        /*var mockPatientRepository = mock(PatientRegistrationRepository.class);

        Patient patient = new Patient();
        PatientUpdateDTO patientUpdateDTO = new PatientUpdateDTO();

        var toTest = new PatientService(mockPatientRepository);

        patient.setPassword("Hasllo123");
        patientUpdateDTO.setPassword("hasllo123");

        patientUpdateDTO.setRepeatedpassword("hasllo123");

        Assertions.assertFalse(toTest.passwordUpdate(patientUpdateDTO,patient));*/

    }

    @Test
    public void is_patient_password_format_ok_test(){

        //given
        var repository = mock(PatientRegistrationRepository.class);
        PatientService patientService = new PatientService(repository);

        //then
        Assertions.assertTrue(patientService.isPasswordFormatOk("Haselko123"));
        Assertions.assertTrue(patientService.isPasswordFormatOk("Czarnanoc1"));
        Assertions.assertFalse(patientService.isPasswordFormatOk("cos"));
    }

}
