package com.example.HANDIPRO;

import com.example.HANDIPRO.Repositories.PhysiotherapistRegistrationRepository;
import com.example.HANDIPRO.models.Physiotherapist;
import com.example.HANDIPRO.services.PhysiotherapistService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PhysiotherapistServiceTest {
    Physiotherapist physiotherapist = new Physiotherapist();

    @Test
    public void isPhysiotherapistEmpty(){
        var mockPhysiotherapistRepository = mock(PhysiotherapistRegistrationRepository.class);
        when(mockPhysiotherapistRepository.findAll()).thenReturn(new ArrayList<>());

        var toTest = new PhysiotherapistService(mockPhysiotherapistRepository);

        var exception = catchThrowable(() -> {
            toTest.readPhysiotherapist(physiotherapist);
        });

        assertThat(exception).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Repository is empty");
    }

    @Test
    public void isPatientPasswordFormatOkTest(){
        var mockPhysiotherapistRepository = mock(PhysiotherapistRegistrationRepository.class);
        var toTest = new PhysiotherapistService(mockPhysiotherapistRepository);

        physiotherapist.setPassword("Haselwdf1");

        Assertions.assertTrue(toTest.isPasswordFormatOk(physiotherapist));
    }
}

