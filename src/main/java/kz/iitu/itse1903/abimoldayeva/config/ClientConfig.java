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
    public void createClient(){
        System.out.println("------------1.create client-----------");
        Client client = Client.builder()
                .age(25)
                .city("NY")
                .email("kz")
                .firstName("New")
                .lastName("Client")
                .sex("female")
                .build();
        clientService.saveClient(client);
    }

    @Bean(name = "allClient")
    public void allClients(){

        System.out.println("------------1.All clients-----------");
        clientService.getAllClients().stream().forEach(client -> System.out.println(client.getFirstName()));
    }

    @Bean
    public void clientById(){
        System.out.println("\n------------2.Client with id = 3-----------");
        Optional<Client> clientById = clientService.getClientById(3L);
        System.out.println(clientById.get().getId() + "." + clientById.get().getFirstName());
    }

    @Bean
    public void clientByName(){
        System.out.println("\n------------3.Client with name Tomiris-----------");
        Client clientByName = clientService.getClientByName("Tomiris");
        System.out.println(clientByName.getId() + " " + clientByName.getFirstName() + " " + clientByName.getCreatedDate());
    }

    @Bean
    public void clientByEmail(){
        System.out.println("\n------------3.Client with email abimoldayevat@gmail.com-----------");
        Client clientByEmail = clientService.getClientByEmail("abimoldayevat@gmail.com");
        System.out.println(clientByEmail.getId() + " " + clientByEmail.getFirstName() + " " + clientByEmail.getEmail());
    }

    @Bean
    public void clientsByCity(){
        System.out.println("\n------------3.Client in Almaty-----------");
        Client clientByCity = clientService.getClientByCity("Almaty");
        //clientByCity.stream().forEach(client -> client.getFirstName());
        System.out.println(clientByCity.getId() + " " + clientByCity.getFirstName() + " " +clientByCity.getCity());

    }


    @Bean
    public void updateClientEmail(){
        System.out.println("\n------------3.Update 3-rd client's email to legend@gmail.com-----------");
        System.out.println("Before updating: " + clientService.getClientById(3L).get().getEmail());
        clientService.updateClientsEmail(3L, "legend@gmail.com");
        System.out.println("After updating: " + clientService.getClientById(3L).get().getEmail());
    }

    @Bean
    @Lazy
    public void deleteClient(){
        System.out.println("\n------------3.Delete 4-th client-----------");
        System.out.println("Before deleting:");
        clientService.getAllClients().stream().forEach(client -> System.out.println(client.getId()+" "+client.getFirstName()));
        Client deletedClient = clientService.getAllClients().stream().filter(client ->
                client.getFirstName().equals("New")).findFirst().get();

        clientService.deleteClient(deletedClient.getId());

        System.out.println("After deleting: ");
        clientService.getAllClients().stream().forEach(client -> System.out.println(client.getId()+" "+client.getFirstName()));
    }

}
