package kz.iitu.itse1903.abimoldayeva.config;

import kz.iitu.itse1903.abimoldayeva.service.TherapistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class TherapistConfig {
    @Autowired
    private TherapistService therapistService;

    @Bean
    public void allTherapists(){
        System.out.println("\n------------6.All therapists-----------");
        therapistService.getAllTherapists().stream().forEach(
                therapist -> System.out.println(therapist.getFirstName()));
    }

    @Bean
    @Profile("therapist")
    public void therapistById(){
        System.out.println("\n------------7. therapist with id = 5-----------");
        System.out.println(therapistService.getTherapistById(5L).get().getFirstName());
    }

    @Bean
    @Profile("!therapist")
    public void therapistByExperience(){
        System.out.println("\n------------8. therapist with experience >= 10-----------");
        therapistService.getTherapistsByExperience(10).get().stream()
                .forEach(therapist -> System.out.println(therapist.getFirstName()
                        + "'s experience: " + therapist.getExperience() + " years"));
    }

}
