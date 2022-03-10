package kz.iitu.itse1903.abimoldayeva.service;

import kz.iitu.itse1903.abimoldayeva.aop.ResourceNotFoundException;
import kz.iitu.itse1903.abimoldayeva.database.Therapist;
import kz.iitu.itse1903.abimoldayeva.repository.SpecializationRepository;
import kz.iitu.itse1903.abimoldayeva.repository.TherapistRepository;
import kz.iitu.itse1903.abimoldayeva.repository.TherapySessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TherapistService {
    @Autowired
    private TherapistRepository therapistRepository;
    @Autowired
    private SpecializationRepository specializationRepository;

    public List<Therapist> getAllTherapists(){
        return therapistRepository.findAll();
    }

    public Therapist getTherapistById(Long id){
        return therapistRepository.getById(id);
    }

    public Therapist saveTherapist(Therapist therapist, Long specializationId){
        return specializationRepository.findById(specializationId).map(specialization -> {
            therapist.setSpecialization(specialization);
            return therapistRepository.save(therapist);
        }).orElseThrow(() -> new ResourceNotFoundException("Specialization id = " + specializationId + " not found"));
    }
}
