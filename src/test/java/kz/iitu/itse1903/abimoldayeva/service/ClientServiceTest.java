package kz.iitu.itse1903.abimoldayeva.service;

import kz.iitu.itse1903.abimoldayeva.database.Client;
import kz.iitu.itse1903.abimoldayeva.repository.ClientRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


class ClientServiceTest {
    @Mock
    ClientRepositoryImpl clientRepository;

    @Mock
    Client client;

    @InjectMocks
    ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private Client client1 = Client.builder()
            .id(1L)
            .firstName("Tomiris")
            .lastName("Abimoldayeva")
            .phone("87780058066")
            .email("abimoldayevat@gmail.com")
            .age(20)
            .sex("female")
            .build();
    @Test
    void testGetAllClients() {
        when(clientRepository.findAllClients()).thenReturn(Arrays.asList(
                new Client(0L, "John", "Doe", "877777777777", "any", "Almaty", 15, "female")));

        List<Client> result = clientService.getAllClients();
        Assertions.assertEquals(Arrays.asList(
                new Client(0L, "John", "Doe", "877777777777", "any", "Almaty", 15, "female")).toString(),
                result.toString());
    }

    @Test
    void testGetClientById() {
        when(clientRepository.findClientById(1L)).thenReturn(Optional.of(client1));

        Client result = clientService.getClientById(1L).get();
        Client expect = client.getClients().get(0);
        Assertions.assertEquals(expect.toString(), result.toString());
    }

    @Test
    void testGetClientByLastName() {
        when(clientRepository.findClientByLastName("Abimoldayeva")).thenReturn(Optional.of(client1));

        Client result = clientService.getClientByLastName("Abimoldayeva").get();
        Client expect = client.getClients().get(0);
        Assertions.assertEquals(expect.toString(), result.toString());
    }
}