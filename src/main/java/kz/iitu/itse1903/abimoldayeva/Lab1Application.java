package kz.iitu.itse1903.abimoldayeva;

import kz.iitu.itse1903.abimoldayeva.database.Client;
import kz.iitu.itse1903.abimoldayeva.service.ClientService;
import kz.iitu.itse1903.abimoldayeva.service.SpecializationService;
import kz.iitu.itse1903.abimoldayeva.service.TherapistService;
import kz.iitu.itse1903.abimoldayeva.service.TherapySessionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Lab1Application {
    private static ApplicationContext applicationContext;
    public static void main(String[] args) {
        applicationContext = SpringApplication.run(Lab1Application.class, args);
        //ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");

        ClientService clientService = applicationContext.getBean(ClientService.class);
        SpecializationService specializationService = applicationContext.getBean(SpecializationService.class);
        TherapistService therapistService = applicationContext.getBean(TherapistService.class);
        TherapySessionService therapySessionService = applicationContext.getBean(TherapySessionService.class);


        //get all clients' names
        System.out.println("------------1.All clients-----------");
        clientService.getAllClients().stream().forEach(client -> System.out.println(client.getFirstName()));

        //get client by id
        System.out.println("\n------------2.Client with id = 2-----------");
        Optional<Client> clientById = clientService.getClientById(2L);
        System.out.println(clientById.get().getId() + "." + clientById.get().getFirstName());

        //get client by lastname
        System.out.println("\n------------3.Client with lastname Del Ray-----------");
        System.out.println(clientService.getClientByLastName("Del Ray").get());

        //get all specializations
        System.out.println("\n------------4.All Specializations-----------");
        specializationService.getAllSpecializations().stream()
                .forEach(specialization -> System.out.println(specialization.toString()));

        //get specialization by id
        System.out.println("\n------------5. specialization with id = 5-----------");
        System.out.println(specializationService.getSpecializationById(5L).get());

        //get all therapists' names
        System.out.println("\n------------6.All therapists-----------");
        therapistService.getAllTherapists().stream().forEach(
                therapist -> System.out.println(therapist.getFirstName()));

        //get therapist by id
        System.out.println("\n------------7. therapist with id = 7-----------");
        System.out.println(therapistService.getTherapistById(5L).get());

        //get therapist by experince
        System.out.println("\n------------8. therapist with experience > 10-----------");
        therapistService.getTherapistsByExperience(10).get().stream()
                .forEach(therapist -> System.out.println(therapist.getFirstName()
                        + "'s experience: " + therapist.getExperience() + " years"));

        //get all sessions
        System.out.println("\n------------9.All sessions-----------");
        therapySessionService.getAllTherapySessions().stream()
                .forEach(ts -> System.out.println("Session " + ts.getId() + ": \n"
                + "    Therapist: " + therapistService.getTherapistById(ts.getTherapist_id()).get().getFirstName()
                        +"\n    Client: " + clientService.getClientById(ts.getClient_id()).get().getFirstName()
                        +"\n    Date: " + ts.getSessionDate() + "\n    Time: " + ts.getSessionTime()) );


        //get session info by date
        System.out.println("\n------------10.Sessions by date 2022-02-09-----------");
        therapySessionService.getTherapySessionByDate(LocalDate.parse("2022-02-09")).get().stream()
                .forEach(ts -> System.out.println("Session " + ts.getId() + ": \n"
                        + "    Therapist: " + therapistService.getTherapistById(ts.getTherapist_id()).get().getFirstName()
                        +"\n    Client: " + clientService.getClientById(ts.getClient_id()).get().getFirstName()
                        +"\n    Date: " + ts.getSessionDate() + "\n    Time: " + ts.getSessionTime()) );

    }

}
