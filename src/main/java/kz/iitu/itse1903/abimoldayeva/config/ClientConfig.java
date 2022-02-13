package kz.iitu.itse1903.abimoldayeva.config;

import kz.iitu.itse1903.abimoldayeva.database.Client;
import kz.iitu.itse1903.abimoldayeva.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;

import java.util.Optional;

@Configuration
public class ClientConfig {
    @Autowired
    private ClientService clientService;

    @Bean
    public void allClients(){
        System.out.println("------------1.All clients-----------");
        clientService.getAllClients().stream().forEach(client -> System.out.println(client.getFirstName()));
    }

    @Bean
    @Lazy
    public void clientById(){
        System.out.println("\n------------2.Client with id = 2-----------");
        Optional<Client> clientById = clientService.getClientById(2L);
        System.out.println(clientById.get().getId() + "." + clientById.get().getFirstName());
    }

}
