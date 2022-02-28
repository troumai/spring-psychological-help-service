package kz.iitu.itse1903.abimoldayeva.service;

import kz.iitu.itse1903.abimoldayeva.database.Client;
import kz.iitu.itse1903.abimoldayeva.database.TherapySession;
import kz.iitu.itse1903.abimoldayeva.repository.ClientRepository;
import kz.iitu.itse1903.abimoldayeva.repository.TherapySessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TherapySessionRepository therapySessionRepository;

    List<Client> clientList = Client.getClients();

    public void createClientsFromList(){
        //clientList.forEach(client -> clientRepository.save(client));
        clientRepository.saveAll(clientList);
    }

    public void createClient(){
    }

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id){
        Optional<Client> client = clientRepository.findById(id);
        return client;
    }

    public Client updateClientsEmail(Long id, String email){
        Client client = clientRepository.getById(id);
        client.setEmail(email);
        return clientRepository.save(client);
    }

    public void deleteClient(Long id){
        clientRepository.deleteById(id);
    }
}
