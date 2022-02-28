package kz.iitu.itse1903.abimoldayeva.service;

import kz.iitu.itse1903.abimoldayeva.database.Specialization;
import kz.iitu.itse1903.abimoldayeva.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecializationService {
    //DI by field injection
    @Autowired
    private SpecializationRepository specializationRepository;

    List<Specialization> specializationList = Specialization.getSpecialization();

    public void createSpecialization(){
       specializationList.forEach(specialization -> specializationRepository.save(specialization));
    }

    public List<Specialization> getAllSpecializations(){
        return specializationRepository.findAll();
    }

    public Optional<Specialization> getSpecializationById(Long id){
        return specializationRepository.findById(id);
    }

    public Specialization updateSpecializationName(Long id, String name){
        Specialization specialization = specializationRepository.getById(id);
        specialization.setName(name);
        return specializationRepository.save(specialization);
    }

    public void deleteSpecialization(Long id){
        specializationRepository.deleteById(id);
    }

}
