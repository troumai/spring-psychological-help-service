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

@Configuration
public class TherapySessionConfig {

    @Autowired
    private TherapySessionService therapySessionService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private TherapistService therapistService;

    @Bean
    @Lazy
    public void createTherapySession(){
        System.out.println("------------1.create sessions-----------");
        TherapySession therapySession = new TherapySession(LocalDate.parse("2022-01-03"), LocalTime.parse("15:00"));
        therapySessionService.saveTherapySession(1L, 1L, therapySession);
    }

    @Bean("allTherapySession")
    public void allTherapySessions(){
        System.out.println("------------1.All sessions-----------");
        therapySessionService.getAllTherapySessions().stream().forEach(
                therapySession -> System.out.println(therapySession.getSessionDate() + " "
                        +therapySession.getClient().getId() + " "
                        +therapySession.getTherapist().getId()
                ));
//        List<Client> clients = clientService.getAllClients();
//        clients.stream().forEach(client -> client.addTherapySession(therapySessions.stream().filter(
//                therapySession -> therapySession.getClient().getId().equals(client.getId())).findFirst().get()));
//        clients.forEach(client -> clientService.saveClients(client));
    }

}
