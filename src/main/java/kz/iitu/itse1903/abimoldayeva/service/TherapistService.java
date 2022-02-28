package kz.iitu.itse1903.abimoldayeva.service;

import kz.iitu.itse1903.abimoldayeva.database.Therapist;
import kz.iitu.itse1903.abimoldayeva.database.TherapySession;
import kz.iitu.itse1903.abimoldayeva.repository.TherapistRepository;
import kz.iitu.itse1903.abimoldayeva.repository.TherapySessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TherapistService {
    @Autowired
    private TherapistRepository therapistRepository;

    @Autowired
    private TherapySessionRepository therapySessionRepository;

    List<Therapist> therapists = Therapist.getTherapists();
//
    public void createTherapistsFromList(){
        therapists.forEach(therapist -> therapistRepository.save(therapist));
    }

    public List<Therapist> getAllTherapists(){
        return therapistRepository.findAll();
    }

    public Optional<Therapist> getTherapistById(Long id){
        return therapistRepository.findById(id);
    }

    public Therapist updateTherapistEmail(Long id, String email){
        Therapist therapist = therapistRepository.getById(id);
        therapist.setEmail(email);
        return therapistRepository.save(therapist);
    }

    public void deleteTherapist(Long id){
        therapistRepository.deleteById(id);
    }
}
