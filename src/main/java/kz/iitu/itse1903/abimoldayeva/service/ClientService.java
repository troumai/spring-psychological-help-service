package kz.iitu.itse1903.abimoldayeva.service;

import kz.iitu.itse1903.abimoldayeva.database.Client;
import kz.iitu.itse1903.abimoldayeva.repository.ClientRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private ClientRepositoryImpl clientRepositoryImpl;

    //DI by Setter
    @Autowired
    public void setClientRepositoryImpl(ClientRepositoryImpl clientRepositoryImpl) {
        this.clientRepositoryImpl = clientRepositoryImpl;
    }

    public List<Client> getAllClients(){
        return clientRepositoryImpl.findAllClients();
    }

    public Optional<Client> getClientById(Long id){
        return clientRepositoryImpl.findClientById(id);
    }

    public Optional<Client> getClientByLastName(String lastname){
        return clientRepositoryImpl.findClientByLastName(lastname);
    }
}
