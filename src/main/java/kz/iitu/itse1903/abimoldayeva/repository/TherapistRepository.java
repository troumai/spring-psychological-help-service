package kz.iitu.itse1903.abimoldayeva.repository;

import kz.iitu.itse1903.abimoldayeva.database.Therapist;

import java.util.List;
import java.util.Optional;

public interface TherapistRepository {
    List<Therapist> findAllTherapists();
    Optional<Therapist> findTherapistById(Long id);
    Optional<List<Therapist>> findTherapistByExperience(int experience);
}
