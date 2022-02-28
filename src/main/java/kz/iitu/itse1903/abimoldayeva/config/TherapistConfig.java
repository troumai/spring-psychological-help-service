package kz.iitu.itse1903.abimoldayeva.config;

import kz.iitu.itse1903.abimoldayeva.service.TherapistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

@Configuration
@Lazy
public class TherapistConfig {
    @Autowired
    private TherapistService therapistService;

    @Bean
    public void createTherapist(){
        therapistService.createTherapistsFromList();
    }

    @Bean
    public void allTherapists(){
        System.out.println("\n------------6.All therapists-----------");
        therapistService.getAllTherapists().stream().forEach(
                therapist -> System.out.println(therapist.getFirstName()));
    }

    @Bean
    public void therapistById(){
        System.out.println("\n------------7. therapist with id = 5-----------");
        System.out.println(therapistService.getTherapistById(1L).get().getFirstName());
    }
}
