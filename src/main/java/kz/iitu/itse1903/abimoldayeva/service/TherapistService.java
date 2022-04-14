package kz.iitu.itse1903.abimoldayeva.service;

import kz.iitu.itse1903.abimoldayeva.database.Therapist;
import kz.iitu.itse1903.abimoldayeva.exception.ResourceNotFoundException;
import kz.iitu.itse1903.abimoldayeva.repository.SpecializationRepository;
import kz.iitu.itse1903.abimoldayeva.repository.TherapistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TherapistService {
    private final TherapistRepository therapistRepository;
    private final SpecializationRepository specializationRepository;

    @Autowired
    public TherapistService(TherapistRepository therapistRepository, SpecializationRepository specializationRepository) {
        this.therapistRepository = therapistRepository;
        this.specializationRepository = specializationRepository;
    }

    public List<Therapist> getAllTherapists(){
        return therapistRepository.findAll();
    }

    public Therapist getTherapistById(Long id){
        return therapistRepository.getById(id);
    }

    public Therapist saveTherapist(Therapist therapist, Long specializationId) throws ResourceNotFoundException {
        return specializationRepository.findById(specializationId).map(specialization -> {
            therapist.setSpecialization(specialization);
            return therapistRepository.saveAndFlush(therapist);
        }).orElseThrow(() -> new ResourceNotFoundException("Specialization id = " + specializationId + " not found"));
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
