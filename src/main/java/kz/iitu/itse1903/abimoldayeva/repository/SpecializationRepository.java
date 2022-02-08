package kz.iitu.itse1903.abimoldayeva.repository;

import kz.iitu.itse1903.abimoldayeva.database.Specialization;

import java.util.List;
import java.util.Optional;

public interface SpecializationRepository {
    List<Specialization> findAllSpecialization();
    Optional<Specialization> findSpecializationById(Long id);
}
