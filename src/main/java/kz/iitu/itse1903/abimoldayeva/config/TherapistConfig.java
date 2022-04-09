package kz.iitu.itse1903.abimoldayeva.config;

import jdk.jfr.Label;
import kz.iitu.itse1903.abimoldayeva.database.Therapist;
import kz.iitu.itse1903.abimoldayeva.service.TherapistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;

@Configuration
public class TherapistConfig {
    @Autowired
    private TherapistService therapistService;

    @Bean
    @Lazy
    public void createTherapist(){
        System.out.println("\n------------6.create therapists-----------");
        Therapist therapist = Therapist.builder()
                .city("Almaty")
                .age(44)
                .experience(6)
                .email("new@gmail.com")
                .firstName("New")
                .lastName("Therapist")
                .sex("male")
                .build();
        therapistService.saveTherapist(therapist, 1L);
    }

    @Bean
    public void allTherapists(){
        System.out.println("\n------------6.All therapists-----------");
        therapistService.getAllTherapists().stream().forEach(
                therapist -> System.out.println(therapist.getFirstName() + " " + therapist.getSpecialization().getId()));
    }

//    @Bean
//    @Profile("therapist")
//    public void therapistById(){
//        System.out.println("\n------------7. therapist with id = 5-----------");
//        System.out.println(therapistService.getTherapistById(5L).get().getFirstName());
//    }
//
//    @Bean
//    @Profile("!therapist")
//    public void therapistByExperience(){
//        System.out.println("\n------------8. therapist with experience >= 10-----------");
//        therapistService.getTherapistsByExperience(10).get().stream()
//                .forEach(therapist -> System.out.println(therapist.getFirstName()
//                        + "'s experience: " + therapist.getExperience() + " years"));
//    }




}
