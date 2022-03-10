package kz.iitu.itse1903.abimoldayeva.config;

import kz.iitu.itse1903.abimoldayeva.database.Specialization;
import kz.iitu.itse1903.abimoldayeva.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class SpecializationConfig {
    @Autowired
    private SpecializationService specializationService;

    @Bean
    @Lazy
    public void createSpecialization(){
        System.out.println("------------1.create specialization-----------");
        Specialization specialization = Specialization.builder()
                .name("Burnout")
                .build();
        specializationService.saveSpecialization(specialization);
    }

    @Bean
    public void allSpecialization(){
        System.out.println("------------1.All specializations-----------");
        specializationService.getAllSpecializations().stream().forEach(
                specialization -> System.out.println(specialization.getName()));
    }
}
