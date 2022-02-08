package kz.iitu.itse1903.abimoldayeva.repository;

import kz.iitu.itse1903.abimoldayeva.database.Client;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepositoryImpl implements ClientRepository{

    List<Client> clientList = Client.getClients();

    @Override
    public List<Client> findAllClients() {
        return clientList;
    }

    @Override
    public Optional<Client> findClientById(Long id) {
        Optional<Client> neededClient = clientList.stream()
            .filter(client -> client.getId().equals(id)).findFirst();

        return Optional.of(neededClient.orElseGet(() -> new Client()));
    }

    @Override
    public Optional<Client> findClientByLastName(String lastname) {
        Optional<Client> neededClient = clientList.stream()
                .filter(client -> client.getLastName().equals(lastname)).findFirst();

        return Optional.of(neededClient.orElseGet(()-> new Client()));
    }
}
