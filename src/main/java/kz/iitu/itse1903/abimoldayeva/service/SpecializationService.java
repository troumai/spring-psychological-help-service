package kz.iitu.itse1903.abimoldayeva.service;

import kz.iitu.itse1903.abimoldayeva.database.Specialization;
import kz.iitu.itse1903.abimoldayeva.repository.SpecializationRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecializationService {
    //DI by field injection
    @Autowired
    private SpecializationRepositoryImpl specializationRepositoryImpl;

    public List<Specialization> getAllSpecializations(){
        return specializationRepositoryImpl.findAllSpecialization();
    }

    public Optional<Specialization> getSpecializationById(Long id){
        return specializationRepositoryImpl.findSpecializationById(id);
    }

}
