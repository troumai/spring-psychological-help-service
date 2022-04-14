package kz.iitu.itse1903.abimoldayeva.service;

import kz.iitu.itse1903.abimoldayeva.database.Client;
import kz.iitu.itse1903.abimoldayeva.exception.ResourceNotFoundException;
import kz.iitu.itse1903.abimoldayeva.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public void saveClient(Client client){
        clientRepository.saveAndFlush(client);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    @Transactional
    public Optional<Client> getClientById(Long id) throws ResourceNotFoundException {
        Optional<Client> client = clientRepository.findById(id);
        if(client.isPresent()){
            return client;
        }else {
            throw new ResourceNotFoundException("Client with id = " + id + " not found");
        }
    }

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public Client getClientByName(String name){
//        return clientRepository.findClientByFirstName(name);
//    }
//
//    @Transactional(rollbackForClassName = {"NullpointerException"})
//    public Client getClientByEmail(String email){
//        return clientRepository.findByEmail(email);
//    }
//
//    public Client getClientByCity(String city){
//        return clientRepository.findByCity(city);
//    }
//
//    public Client updateClient(Long clientId, Client client) throws UserNotFoundException{
//        return clientRepository.findById(clientId).map(clientUpdate -> {
//            clientUpdate.setAge(client.getAge());
//            clientUpdate.setEmail(client.getEmail());
//            clientUpdate.setCity(client.getCity());
//            clientUpdate.setFirstName(client.getFirstName());
//            clientUpdate.setLastName(client.getLastName());
//            clientUpdate.setSex(client.getSex());
//            return clientRepository.saveAndFlush(clientUpdate);
//        }).orElseThrow(() -> new UserNotFoundException("Client id = " + clientId + " not found"));
//    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Client updateClientsEmail(Long id, String email){
        Client client = clientRepository.getById(id);
        client.setEmail(email);
        return clientRepository.save(client);
    }

    public void deleteClient(long id){
        clientRepository.deleteById(id);
    }

    @Transactional
    public List<Client> findPaginated(int pageNo, int pageSize){
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Client> pagedResult = clientRepository.findAll(paging);
        return pagedResult.toList();
    }

}
