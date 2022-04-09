package kz.iitu.itse1903.abimoldayeva.config;

import kz.iitu.itse1903.abimoldayeva.database.Specialization;
import kz.iitu.itse1903.abimoldayeva.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.Optional;

@Configuration
public class SpecializationConfig {
    @Autowired
    private SpecializationService specializationService;

    @Bean
    @Lazy
    public void createSpecialization(){
        System.out.println("------------1.create specialization-----------");
        Specialization specialization = Specialization.builder()
                .name("New Spec")
                .build();
        specializationService.saveSpecialization(specialization);
    }

    @Bean
    public void allSpecialization(){
        System.out.println("------------1.All specializations-----------");
        specializationService.getAllSpecializations().stream().forEach(
                specialization -> System.out.println(specialization.getName()));
    }

    @Bean
    public void getSpecializationById(){
        Optional<Specialization> specialization = specializationService.getSpecializationById(1L);
        System.out.println(specialization.get().getId() + " " + specialization.get().getName());
    }

    @Bean
    public void updateSpecialization(){
        System.out.println("\n------------3.Update specialization's name-----------");
        System.out.println("Before updating: " + specializationService.getSpecializationById(3L).get().getName());
        specializationService.updateSpecializationName(3L, "Love");
        System.out.println("After updating: " + specializationService.getSpecializationById(3L).get().getName());
    }

    @Bean
    @Lazy
    public void deleteSpecialization(){
        System.out.println("\n------------4.Delete specialization-----------");
        System.out.println("Before deleting: ");
        specializationService.getAllSpecializations().stream().forEach(specialization ->
                System.out.println(specialization.getName()));
        specializationService.deleteSpecialization(4L);
        System.out.println("After deleting: ");
        specializationService.getAllSpecializations().stream().forEach(specialization ->
                System.out.println(specialization.getName()));
    }
}
