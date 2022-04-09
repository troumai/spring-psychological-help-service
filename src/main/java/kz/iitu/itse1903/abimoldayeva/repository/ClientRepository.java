package kz.iitu.itse1903.abimoldayeva.repository;

import kz.iitu.itse1903.abimoldayeva.database.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    //Query creation from method names + Locking
    @Lock(LockModeType.PESSIMISTIC_READ)
    Client findClientByFirstName(String name);

    //Annotation based named query configuration
    Client findByEmail(String email);

    //@Query + SpEL expression
    @Query("select c from #{#entityName} c where c.city = ?1")
    Client findByCity(String city);

}
