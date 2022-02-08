package kz.iitu.itse1903.abimoldayeva.repository;

import kz.iitu.itse1903.abimoldayeva.database.Specialization;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SpecializationRepositoryImpl implements SpecializationRepository{
    List<Specialization> specializationList = Specialization.getSpecialization();

    @Override
    public List<Specialization> findAllSpecialization() {
        return specializationList;
    }

    @Override
    public Optional<Specialization> findSpecializationById(Long id) {
        Optional<Specialization> optionalSpecialization = specializationList.stream()
                        .filter(specialization -> specialization.getId().equals(id))
                        .findFirst();

        return Optional.of(optionalSpecialization.orElseGet(() -> new Specialization()));
    }
}
