package kz.iitu.itse1903.abimoldayeva.config;

import kz.iitu.itse1903.abimoldayeva.database.Client;
import kz.iitu.itse1903.abimoldayeva.repository.ClientRepository;
import kz.iitu.itse1903.abimoldayeva.service.ClientService;
import kz.iitu.itse1903.abimoldayeva.service.TherapySessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

import java.util.List;
import java.util.Optional;

@Configuration
public class ClientConfig {
    @Autowired
    private ClientService clientService;

    @Autowired
    private TherapySessionService therapySessionService;

    @Bean
    @Lazy
    public void createClient(){
        System.out.println("------------1.create client-----------");
        Client client = Client.builder()
                .age(25)
                .city("Almaty")
                .email("abimoldayevat@gmail.com")
                .firstName("Tomiris")
                .lastName("Abimoldayeva")
                .sex("female")
                .build();
        clientService.saveClient(client);
    }

    @Bean(name = "allClient")
    public void allClients(){

        System.out.println("------------1.All clients-----------");
        clientService.getAllClients().stream().forEach(client -> System.out.println(client.getFirstName()));
    }

//    @Bean
//    @Lazy
//    public void clientById(){
//        System.out.println("\n------------2.Client with id = 2-----------");
//        Optional<Client> clientById = clientService.getClientById(2L);
//        System.out.println(clientById.get().getId() + "." + clientById.get().getFirstName());
//    }

}
