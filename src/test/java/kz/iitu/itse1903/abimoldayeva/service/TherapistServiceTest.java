package kz.iitu.itse1903.abimoldayeva.service;

import kz.iitu.itse1903.abimoldayeva.database.Client;
import kz.iitu.itse1903.abimoldayeva.database.Specialization;
import kz.iitu.itse1903.abimoldayeva.database.Therapist;
import kz.iitu.itse1903.abimoldayeva.exception.ResourceNotFoundException;
import kz.iitu.itse1903.abimoldayeva.repository.SpecializationRepository;
import kz.iitu.itse1903.abimoldayeva.repository.TherapistRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TherapistServiceTest {
    @Mock
    TherapistRepository therapistRepository;
    @Mock
    SpecializationRepository specializationRepository;
    @InjectMocks
    TherapistService therapistService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllTherapists() {
        List<Therapist> therapists = new ArrayList<>();
        Therapist therapist = new Therapist(1L, "firstName",
                "lastName", "email", "city", 16,
                "sex", 60, new Specialization(1L, "Love"));

        therapists.add(therapist);

        given(therapistRepository.findAll()).willReturn(therapists);
        List<Therapist> actual = therapistService.getAllTherapists();

        Assertions.assertEquals(therapists, actual);
        verify(therapistRepository).findAll();
    }

    @Test
    void testGetTherapistById() {
        Therapist therapist = new Therapist(1L, "firstName",
                "lastName", "email", "city", 16,
                "sex", 60, new Specialization(1L, "Love"));

        given(therapistRepository.getById(therapist.getId())).willReturn(therapist);

        Therapist result = therapistService.getTherapistById(therapist.getId());
        Assertions.assertEquals(therapist, result);

    }

    @Test
    void testSaveTherapist() throws ResourceNotFoundException {
        Therapist therapist = new Therapist(1L, "firstName",
                "lastName", "email", "city", 16,
                "sex", 60, new Specialization(1L, "Love"));

        when(specializationRepository.findById(therapist.getSpecialization().getId())).thenReturn(java.util.Optional.ofNullable(therapist.getSpecialization()));
        when(therapistRepository.saveAndFlush(therapist)).thenReturn(therapist);

        Therapist actual = therapistService.saveTherapist(therapist, 1L);
        Assertions.assertEquals(therapist, actual);
    }

    @Test
    void testSaveTherapistFindSpecializationShouldReturnException(){
        Therapist therapist = new Therapist(1L, "firstName",
                "lastName", "email", "city", 16,
                "sex", 60, new Specialization(1L, "Love"));

        when(specializationRepository.findById(therapist.getSpecialization().getId())).thenReturn(Optional.ofNullable(null));
        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            therapistService.saveTherapist(therapist, therapist.getSpecialization().getId());
        });
        String expectedMessage = "Specialization id = " + therapist.getSpecialization().getId() + " not found";

        Assertions.assertEquals(expectedMessage, exception.getMessage());

    }

    @Test
    void testUpdateTherapistEmail() {
        Therapist therapist = new Therapist();
        therapist.setId(90L);
        therapist.setEmail("oldEmail");
        therapist.setFirstName("Therapist");

        String newEmail = "newEmail";

        given(therapistRepository.getById(therapist.getId())).willReturn(therapist);
        therapist.setEmail(newEmail);
        when(therapistRepository.save(ArgumentMatchers.any(Therapist.class))).thenReturn(therapist);

        Therapist therapist1 = therapistService.updateTherapistEmail(therapist.getId(), newEmail);

        verify(therapistRepository).save(therapist1);
        verify(therapistRepository).getById(therapist.getId());
    }

    @Test
    void testDeleteTherapist() {
        Therapist therapist = new Therapist(1L, "firstName",
                "lastName", "email", "city", 16,
                "sex", 60, new Specialization(1L, "Love"));

        when(therapistRepository.findById(therapist.getId())).thenReturn(java.util.Optional.of(therapist));

        therapistService.deleteTherapist(therapist.getId());
        verify(therapistRepository).deleteById(therapist.getId());
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme