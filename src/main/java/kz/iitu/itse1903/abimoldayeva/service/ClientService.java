package kz.iitu.itse1903.abimoldayeva.service;

import kz.iitu.itse1903.abimoldayeva.aop.ResourceNotFoundException;
import kz.iitu.itse1903.abimoldayeva.database.Client;
import kz.iitu.itse1903.abimoldayeva.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public void saveClient(Client client){
        clientRepository.saveAndFlush(client);
    }

    @Cacheable("clients")
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Client> getClientById(Long id){
        return clientRepository.findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Client getClientByName(String name){
        return clientRepository.findClientByFirstName(name);
    }

    @Transactional(rollbackForClassName = {"NullpointerException"})
    public Client getClientByEmail(String email){
        return clientRepository.findByEmail(email);
    }

    public Client getClientByCity(String city){
        return clientRepository.findByCity(city);
    }

    public Client updateClient(Long clientId, Client client){
        return clientRepository.findById(clientId).map(clientUpdate -> {
            clientUpdate.setAge(client.getAge());
            clientUpdate.setEmail(client.getEmail());
            clientUpdate.setCity(client.getCity());
            clientUpdate.setFirstName(client.getFirstName());
            clientUpdate.setLastName(client.getLastName());
            clientUpdate.setSex(client.getSex());
            return clientRepository.saveAndFlush(clientUpdate);
        }).orElseThrow(() -> new ResourceNotFoundException("Client id = " + clientId + " not found"));
    }

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
    @Scheduled(fixedRateString = "${fixed-rate.in.milliseconds}")
    public void updateSex(){
        List<Client> clients = clientRepository.findAll();
        clients.stream().map(client -> {
            if(client.getSex().equals("female")) client.setSex("woman");
            return client;
        }).forEach(client -> saveClient(client));

        long now = System.currentTimeMillis() / 1000;
        System.out.println(
                "Fixed rate task - " + now);

    }

    @Transactional
    @Scheduled(fixedDelayString = "${fixed-delay.in.milliseconds}")
    public void listClientsInAlmaty(){
        List<Client> clients = clientRepository.findAll();
        clients.stream().filter(client -> client.getCity().equals("Almaty"))
                .forEach(client -> System.out.println(client.getFirstName()));

        long now = System.currentTimeMillis() / 1000;
        System.out.println(
                "Fixed delay task - " + now);
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void scheduleFixedRateWithInitialDelayTask() {

        long now = System.currentTimeMillis() / 1000;
        System.out.println(
                "Fixed rate task with one second initial delay - " + now);
    }

    //<minute> <hour> <day-of-month> <month> <day-of-week> <command>
    @Scheduled(cron = "${cron.expression}")
    @Async("threadPoolTaskExecutor")
    public void scheduleWithCronExp() {
        long now = System.currentTimeMillis() / 1000;
        System.out.println(
                "Scheduled using cron - " + now);
    }


}
