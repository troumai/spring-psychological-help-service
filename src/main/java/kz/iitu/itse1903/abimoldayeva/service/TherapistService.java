package kz.iitu.itse1903.abimoldayeva.service;

import kz.iitu.itse1903.abimoldayeva.database.Therapist;
import kz.iitu.itse1903.abimoldayeva.repository.TherapistRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TherapistService {
    private TherapistRepositoryImpl therapistRepositoryImpl;

    //DI by constructor
    @Autowired
    public TherapistService(TherapistRepositoryImpl therapistRepositoryImpl) {
        this.therapistRepositoryImpl = therapistRepositoryImpl;
    }

    public List<Therapist> getAllTherapists(){
        return therapistRepositoryImpl.findAllTherapists();
    }

    public Optional<Therapist> getTherapistById(Long id){
        return therapistRepositoryImpl.findTherapistById(id);
    }

    public Optional<List<Therapist>> getTherapistsByExperience(int experience){
        return therapistRepositoryImpl.findTherapistByExperience(experience);
    }
}
