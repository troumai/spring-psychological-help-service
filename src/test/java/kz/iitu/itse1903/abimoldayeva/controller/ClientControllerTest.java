package kz.iitu.itse1903.abimoldayeva.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.iitu.itse1903.abimoldayeva.Lab1Application;
import kz.iitu.itse1903.abimoldayeva.database.Client;
import kz.iitu.itse1903.abimoldayeva.database.TherapySession;
import kz.iitu.itse1903.abimoldayeva.repository.ClientRepository;
import kz.iitu.itse1903.abimoldayeva.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
//                classes = Lab1Application.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Lab1Application.class})
@WebAppConfiguration
@AutoConfigureMockMvc
class ClientControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    private Client client;

    @BeforeEach
    public void setup() throws Exception {

//        Client client = new Client(1L, "Tomiris", "Abimoldayeva", "abimoldayevat@gmail.com",
//                "Almaty", 20, "woman", new HashSet<TherapySession>());
         client = new Client(63L, "John", "Doe", "john@gmail.com",
                "Almaty", 65, "male", new HashSet<TherapySession>());
        when(clientRepository.findById(63l)).thenReturn(java.util.Optional.of(client));
    }

    @Test
    void testGetClientById() throws Exception {
        mockMvc.perform(get("/client/63"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(63)))
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.lastName", is("Doe")))
                .andExpect(jsonPath("$.email", is("john@gmail.com")))
                .andExpect(jsonPath("$.city", is("Almaty")))
                .andExpect(jsonPath("$.age", is(65)))
                .andExpect(jsonPath("$.sex", is("male")));

//        verify(clientRepository).findById(1L);
    }

    @Test
    void testGetAllClients() throws Exception {
        mockMvc.perform(get("/client/getAll"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].firstName", is("John")))
                .andExpect(jsonPath("$[1].firstName", is("John")))
                .andExpect(jsonPath("$[2].firstName", is("Test")))
                .andExpect(jsonPath("$[3].firstName", is("Tomiris")))
                .andExpect(jsonPath("$[4].firstName", is("Brown")));
    }

    @Test
    @Transactional
    void testDeleteClient() throws Exception {
        doNothing().when(clientRepository).deleteById(69L);

        mockMvc.perform(delete("/client/1/delete"))
                .andExpect(status().isOk());

    }

    @Test
    @Transactional
    void testUpdateClientEmail() throws Exception {
        Client client1 = new Client(69L, "Test", "Test", "newEmail", "Almaty", 46, "male", new HashSet<TherapySession>());
        when(clientRepository.save(any(Client.class))).thenReturn(client1);

        mockMvc.perform(put("/client/69/update").param("email", client1.getEmail()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(69)))
                .andExpect(jsonPath("$.email", is("newEmail")));
    }

    @Test
    @Transactional
    void testCreateClient() throws Exception {
        Client client = new Client(100L, "Test", "Test", "john@gmail.com", "Almaty", 46, "male", new HashSet<TherapySession>());
        when(clientRepository.saveAndFlush(any(Client.class))).thenReturn(client);

        mockMvc.perform(post("/client/createClient")
                .param("firstName", client.getFirstName())
                .param("lastName", client.getLastName())
                .param("email", client.getEmail())
                .param("city", client.getCity())
                .param("age", String.valueOf(client.getAge()))
                .param("sex", client.getSex())
                .flashAttr("clientEntity", new Client()))
                .andExpect(status().isCreated());
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme