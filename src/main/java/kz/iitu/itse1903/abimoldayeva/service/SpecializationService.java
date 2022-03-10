package kz.iitu.itse1903.abimoldayeva.service;

import kz.iitu.itse1903.abimoldayeva.aop.ResourceNotFoundException;
import kz.iitu.itse1903.abimoldayeva.database.Specialization;
import kz.iitu.itse1903.abimoldayeva.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecializationService {

    @Autowired
    private SpecializationRepository specializationRepository;

    public List<Specialization> getAllSpecializations(){
        return specializationRepository.findAll();
    }

    public Specialization getSpecializationById(Long id){
        return specializationRepository.findById(id).get();
    }

    public void saveSpecialization(Specialization specialization){
        specializationRepository.save(specialization);
    }

    public Specialization updateSpecialization(Long specializationId, Specialization specialization){
        return specializationRepository.findById(specializationId).map(specializationUpdate -> {
            specializationUpdate.setName(specialization.getName());
            return specializationRepository.save(specializationUpdate);
        }).orElseThrow(() -> new ResourceNotFoundException("Specialization id = " + specializationId + " not found"));
    }


}
