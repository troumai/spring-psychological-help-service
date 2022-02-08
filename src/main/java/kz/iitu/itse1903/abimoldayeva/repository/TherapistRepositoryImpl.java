package kz.iitu.itse1903.abimoldayeva.repository;

import kz.iitu.itse1903.abimoldayeva.database.Therapist;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TherapistRepositoryImpl implements TherapistRepository{
    List<Therapist> therapistList = Therapist.getTherapists();
    @Override
    public List<Therapist> findAllTherapists() {
        return therapistList;
    }

    @Override
    public Optional<Therapist> findTherapistById(Long id) {
        Optional<Therapist> optionalTherapist = therapistList.stream()
                .filter(therapist -> therapist.getId().equals(id)).findFirst();

        return Optional.of(optionalTherapist.orElseGet(() -> new Therapist()));
    }

    @Override
    public Optional<List<Therapist>> findTherapistByExperience(int experience) {
        List<Therapist> therapistListByExperience = therapistList.stream()
                .filter(therapist -> therapist.getExperience() >= experience).collect(Collectors.toList());

        return Optional.of(therapistListByExperience);
    }
}
