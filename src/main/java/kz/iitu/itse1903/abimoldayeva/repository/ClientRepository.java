package kz.iitu.itse1903.abimoldayeva.repository;

import kz.iitu.itse1903.abimoldayeva.database.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
