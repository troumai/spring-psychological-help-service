package kz.iitu.itse1903.abimoldayeva.config;

import kz.iitu.itse1903.abimoldayeva.database.Client;
import kz.iitu.itse1903.abimoldayeva.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;

import java.util.Optional;

@Configuration
public class ClientConfig {
    @Autowired
    private ClientService clientService;

    @Bean
    public void createClients(){
        System.out.println("--------Create clients------------");
        clientService.createClientsFromList();
    }

    @Bean
    public void allClients(){
        System.out.println("------------1.All clients-----------");
        clientService.getAllClients().stream().forEach(client -> System.out.println(client.getId()+" "+client.getFirstName()));
    }

    @Bean
    public void clientById(){
        System.out.println("\n------------2.Client with id = 2-----------");
        Optional<Client> clientById = clientService.getClientById(2L);
        System.out.println(clientById.get().getId() + "." + clientById.get().getFirstName());
    }

    @Bean
    public void updateClientEmail(){
        System.out.println("\n------------3.Update 3-rd client's email to legend@gmail.com-----------");
        System.out.println("Before updating: " + clientService.getClientById(3L).get().getEmail());
        clientService.updateClientsEmail(3L, "legend@gmail.com");
        System.out.println("After updating: " + clientService.getClientById(3L).get().getEmail());
    }

    @Bean
    public void deleteClient(){
        System.out.println("\n------------3.Delete 4-th client-----------");
        System.out.println("Before deleting:");
        clientService.getAllClients().stream().forEach(client -> System.out.println(client.getId()+" "+client.getFirstName()));
        clientService.deleteClient(4L);
        System.out.println("After deleting: ");
        clientService.getAllClients().stream().forEach(client -> System.out.println(client.getId()+" "+client.getFirstName()));
    }

}
