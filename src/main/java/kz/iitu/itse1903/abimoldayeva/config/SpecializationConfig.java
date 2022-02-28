package kz.iitu.itse1903.abimoldayeva.config;

import kz.iitu.itse1903.abimoldayeva.database.Specialization;
import kz.iitu.itse1903.abimoldayeva.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.Optional;

@Configuration
@Lazy
public class SpecializationConfig {
    @Autowired
    private SpecializationService specializationService;

    @Bean
    public void createSpecializations(){
        specializationService.createSpecialization();
    }

    @Bean
    public void getAllSpecializations(){
        specializationService.getAllSpecializations().stream().forEach(
                specialization -> System.out.println(specialization.getId()+" "+specialization.getName()));
    }

    @Bean
    public void getSpecializationById(){
        Optional<Specialization> specialization = specializationService.getSpecializationById(1L);
        System.out.println(specialization.get().getId() + " " + specialization.get().getName());
    }

    @Bean
    @Lazy
    public void updateSpecialization(){
        System.out.println("\n------------3.Update specialization's name-----------");
        System.out.println("Before updating: " + specializationService.getSpecializationById(3L).get().getName());
        specializationService.updateSpecializationName(4L, "Love");
        System.out.println("After updating: " + specializationService.getSpecializationById(4L).get().getName());
    }

}
