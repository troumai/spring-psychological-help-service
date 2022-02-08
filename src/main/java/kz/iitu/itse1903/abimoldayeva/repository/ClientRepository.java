package kz.iitu.itse1903.abimoldayeva.repository;

import kz.iitu.itse1903.abimoldayeva.database.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    List<Client> findAllClients();
    Optional<Client> findClientById(Long id);
    Optional<Client> findClientByLastName(String lastname);
}
