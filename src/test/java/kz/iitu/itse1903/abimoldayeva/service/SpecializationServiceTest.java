package kz.iitu.itse1903.abimoldayeva.service;

import kz.iitu.itse1903.abimoldayeva.database.Client;
import kz.iitu.itse1903.abimoldayeva.database.Specialization;
import kz.iitu.itse1903.abimoldayeva.exception.ResourceNotFoundException;
import kz.iitu.itse1903.abimoldayeva.repository.SpecializationRepository;
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

class SpecializationServiceTest {
    @Mock
    SpecializationRepository specializationRepository;
    @InjectMocks
    SpecializationService specializationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllSpecializations() {
        List<Specialization> specializations = new ArrayList<>();
        Specialization specialization = new Specialization(1L, "Love");
        specializations.add(specialization);

        given(specializationRepository.findAll()).willReturn(specializations);
        List<Specialization> actual = specializationService.getAllSpecializations();

        Assertions.assertEquals(specializations, actual);
        verify(specializationRepository).findAll();
    }

    @Test
    void testGetSpecializationById() {
        Specialization specialization = new Specialization(1L, "Love");

        given(specializationRepository.findById(specialization.getId())).willReturn(Optional.of(specialization));

        Optional<Specialization> actual = specializationService.getSpecializationById(specialization.getId());

        Assertions.assertEquals(specialization, actual.get());
    }

    @Test
    void testSaveSpecialization() {
        Specialization specialization = new Specialization(1L, "Love");

        when(specializationRepository.saveAndFlush(ArgumentMatchers.any(Specialization.class))).thenReturn(specialization);

        Specialization actual = specializationService.saveSpecialization(specialization);

        Assertions.assertEquals(specialization, actual);
    }

    @Test
    void testUpdateSpecializationName() throws ResourceNotFoundException {
        Specialization specialization = new Specialization(1L, "Love");
        String newName = "Stress";

        given(specializationRepository.getById(specialization.getId())).willReturn(specialization);
        specialization.setName(newName);
        when(specializationRepository.saveAndFlush(ArgumentMatchers.any(Specialization.class))).thenReturn(specialization);

        Specialization upd = specializationService.updateSpecializationName(specialization.getId(), newName);

        verify(specializationRepository).saveAndFlush(upd);
        verify(specializationRepository).getById(specialization.getId());
    }

    @Test
    void testDeleteSpecialization() {
        Specialization specialization = new Specialization(1L, "Love");

        when(specializationRepository.findById(specialization.getId())).thenReturn(Optional.of(specialization));

        specializationService.deleteSpecialization(specialization.getId());
        verify(specializationRepository).deleteById(specialization.getId());
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme