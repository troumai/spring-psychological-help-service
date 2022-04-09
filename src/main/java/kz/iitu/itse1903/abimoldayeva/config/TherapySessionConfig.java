package kz.iitu.itse1903.abimoldayeva.config;

import kz.iitu.itse1903.abimoldayeva.database.Client;
import kz.iitu.itse1903.abimoldayeva.database.Therapist;
import kz.iitu.itse1903.abimoldayeva.database.TherapySession;
import kz.iitu.itse1903.abimoldayeva.service.ClientService;
import kz.iitu.itse1903.abimoldayeva.service.TherapistService;
import kz.iitu.itse1903.abimoldayeva.service.TherapySessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Configuration
public class TherapySessionConfig {

    @Autowired
    private TherapySessionService therapySessionService;

    @Bean
    @Lazy
    public void createTherapySession(){
        System.out.println("------------1.create sessions-----------");
        TherapySession therapySession = new TherapySession(LocalDate.parse("2022-01-03"), LocalTime.parse("15:00"));
        therapySessionService.saveTherapySession(4L, 4L, therapySession);
    }

    @Bean
    public void allTherapySessions(){
        System.out.println("------------1.All sessions-----------");
        therapySessionService.getAllTherapySessions().stream().forEach(
                therapySession -> System.out.println(therapySession.getSessionDate() + " "
                        +therapySession.getClient().getId() + " "
                        +therapySession.getTherapist().getId()
                ));
    }

    @Bean
    public void getTherapySessionByDate(){
        Optional<List<TherapySession>> therapySessionList = therapySessionService
                .getTherapySessionByDate(LocalDate.parse("2022-03-03"));
        therapySessionList.get().forEach(therapySession -> System.out.println(
                therapySession.getId() + " " +
                        therapySession.getSessionTime()));
    }

    @Bean
    public void updateTherapySessionDate(){
        System.out.println("\n------------3.Update therapy session date-----------");
        System.out.println("Before updating: ");
        therapySessionService.getTherapySessionByDate(LocalDate.parse("2022-03-11")).get()
                .forEach(therapySession -> System.out.println(therapySession.getId() + " " + therapySession.getSessionDate()));
        therapySessionService.updateTherapySessionDate(2L, LocalDate.parse("2022-03-12"));
        System.out.println("After updating: ");
        therapySessionService.getTherapySessionByDate(LocalDate.parse("2022-03-12")).get()
                .forEach(therapySession -> System.out.println(therapySession.getId() + " " + therapySession.getSessionDate()));

    }

}
