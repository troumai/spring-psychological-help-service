package kz.iitu.itse1903.abimoldayeva.service;

import kz.iitu.itse1903.abimoldayeva.database.Client;
import kz.iitu.itse1903.abimoldayeva.database.Specialization;
import kz.iitu.itse1903.abimoldayeva.exception.ResourceNotFoundException;
import kz.iitu.itse1903.abimoldayeva.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ClientServiceTest {
    @Mock
    ClientRepository clientRepository;
    @InjectMocks
    ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveClient() {
        Client client = new Client("firstName", "lastName", "email", "city", 16, "sex");

        when(clientRepository.saveAndFlush(ArgumentMatchers.any(Client.class))).thenReturn(client);

        Client result = clientService.saveClient(client);

        Assertions.assertSame(result.getFirstName(), client.getFirstName());
    }

    @Test
    void testGetAllClients() {
        List<Client> clients = new ArrayList<>();

        clients.add(new Client());

        given(clientRepository.findAll()).willReturn(clients);

        List<Client> expected = clientService.getAllClients();

        Assertions.assertEquals(expected, clients);

        verify(clientRepository).findAll();
    }

    @Test
    void testGetClientById() throws ResourceNotFoundException {

        Client client = new Client();
        client.setId(90L);
        client.setFirstName("Client");

        given(clientRepository.findById(client.getId())).willReturn(Optional.of(client));

        Optional<Client> result = clientService.getClientById(client.getId());
        Assertions.assertEquals(result.get(), client);
    }

    @Test
    void testGetClientByIdShouldReturnException(){
        Client client = new Client();
        client.setId(89L);
        client.setFirstName("Client");

        when(clientRepository.findById(client.getId())).thenReturn(Optional.ofNullable(null));

        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () ->{
            clientService.getClientById(client.getId());
        });

        String expected = "Client with id = " + client.getId() + " not found";

        Assertions.assertEquals(expected, exception.getMessage());
    }

    @Test
    void testUpdateClientsEmail() {
        Client client = new Client();
        client.setId(90L);
        client.setEmail("clientEmail");
        client.setFirstName("Client");

        String newEmail = "newEmail";

        given(clientRepository.getById(client.getId())).willReturn(client);
        client.setEmail(newEmail);
        when(clientRepository.save(ArgumentMatchers.any(Client.class))).thenReturn(client);

        Client client1 = clientService.updateClientsEmail(client.getId(), newEmail);

        verify(clientRepository).save(client1);
        verify(clientRepository).getById(client.getId());
    }

    @Test
    void testDeleteClient() {
        Client client = new Client();
        client.setFirstName("Client");
        client.setId(1L);

        when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));

        clientService.deleteClient(client.getId());

        verify(clientRepository).deleteById(client.getId());
    }

    @Test
    void testFindPaginated() {
        List<Client> result = new ArrayList<>();

        Client client = new Client();
        client.setId(1L);
        client.setFirstName("Client");

        result.add(client);

        Pageable paging = PageRequest.of(0, 1);

        Page<Client> expected = new PageImpl<>(result);

        when(clientRepository.findAll(paging)).thenReturn(expected);

        List<Client> res = clientService.findPaginated(0, 1);

        Assertions.assertEquals(result, res);
        verify(clientRepository).findAll(paging);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme