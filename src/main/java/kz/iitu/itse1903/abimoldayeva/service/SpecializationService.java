package kz.iitu.itse1903.abimoldayeva.service;

import kz.iitu.itse1903.abimoldayeva.database.Specialization;
import kz.iitu.itse1903.abimoldayeva.exception.ResourceNotFoundException;
import kz.iitu.itse1903.abimoldayeva.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SpecializationService {

    private final SpecializationRepository specializationRepository;

    @Autowired
    public SpecializationService(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    @Transactional
    public List<Specialization> getAllSpecializations(){
        return specializationRepository.findAll();
    }

    public Optional<Specialization> getSpecializationById(Long id){
        return specializationRepository.findById(id);
    }

    public void saveSpecialization(Specialization specialization){
        specializationRepository.saveAndFlush(specialization);
    }

    public Specialization updateSpecialization(Long specializationId, Specialization specialization) throws ResourceNotFoundException {
        return specializationRepository.findById(specializationId).map(specializationUpdate -> {
            specializationUpdate.setName(specialization.getName());
            return specializationRepository.saveAndFlush(specializationUpdate);
        }).orElseThrow(() -> new ResourceNotFoundException("Specialization id = " + specializationId + " not found"));
    }

    public Specialization updateSpecializationName(Long id, String name){
        Specialization specialization = specializationRepository.getById(id);
        specialization.setName(name);
        return specializationRepository.saveAndFlush(specialization);
    }

    public void deleteSpecialization(Long id){
        specializationRepository.deleteById(id);
    }


}
