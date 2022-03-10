package kz.iitu.itse1903.abimoldayeva.service;

import kz.iitu.itse1903.abimoldayeva.aop.ResourceNotFoundException;
import kz.iitu.itse1903.abimoldayeva.database.Client;
import kz.iitu.itse1903.abimoldayeva.repository.ClientRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public void saveClient(Client client){
        clientRepository.save(client);
    }

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public Client getClientById(Long id){
        return clientRepository.findById(id).get();
    }

    public Client updateClient(Long clientId, Client client){
        return clientRepository.findById(clientId).map(clientUpdate -> {
            clientUpdate.setAge(client.getAge());
            clientUpdate.setEmail(client.getEmail());
            clientUpdate.setCity(client.getCity());
            clientUpdate.setFirstName(client.getFirstName());
            clientUpdate.setLastName(client.getLastName());
            clientUpdate.setSex(client.getSex());
            return clientRepository.save(clientUpdate);
        }).orElseThrow(() -> new ResourceNotFoundException("Client id = " + clientId + " not found"));
    }

}
