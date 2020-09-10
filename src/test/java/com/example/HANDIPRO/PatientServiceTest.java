package com.example.HANDIPRO;

import com.example.HANDIPRO.Repositories.PatientRegistrationRepository;
import com.example.HANDIPRO.models.Patient;
import com.example.HANDIPRO.services.PatientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PatientServiceTest {

    Patient patient = new Patient();

    @Test
    public void isReadPatientEmptyTest(){
        var mockPatientRepository = mock(PatientRegistrationRepository.class);
        when(mockPatientRepository.findAll()).thenReturn(new ArrayList<>());

        var toTest = new PatientService(mockPatientRepository);

        var exception = catchThrowable(() -> {
            toTest.readPatient();
        });

        assertThat(exception).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Repository is empty");
    }

    @Test
    public void isPatientPasswordFormatOkTest(){
        var mockPatientRepository = mock(PatientRegistrationRepository.class);
        var toTest = new PatientService(mockPatientRepository);

        patient.setPassword("Haselwdf1");

        Assertions.assertTrue(toTest.isPasswordFormatOk(patient));
    }

}
