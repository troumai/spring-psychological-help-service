package kz.iitu.itse1903.abimoldayeva.controller;

import kz.iitu.itse1903.abimoldayeva.database.Client;
import kz.iitu.itse1903.abimoldayeva.exception.ResourceNotFoundException;
import kz.iitu.itse1903.abimoldayeva.service.ClientService;
import kz.iitu.itse1903.abimoldayeva.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/client")
@Slf4j
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final KafkaProducerService producer;

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    @PostMapping("/createClient")
    @ResponseBody
    public ResponseEntity<String> createClient(@Valid @ModelAttribute("clientEntity") Client client){
        try{
            clientService.saveClient(new Client(
                    client.getFirstName(),
                    client.getLastName(),
                    client.getEmail(),
                    client.getCity(),
                    client.getAge(),
                    client.getSex()));
            return new ResponseEntity<>("Client created", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") long id) throws ResourceNotFoundException {
        Optional<Client> client = clientService.getClientById(id);
        return new ResponseEntity<>(client.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    @ResponseBody
    public ResponseEntity<Client> updateClientEmail(@PathVariable("id") long id, @RequestParam String email) throws ResourceNotFoundException {
        Optional<Client> client1 = clientService.getClientById(id);
        if(client1.isPresent()){
            client1 = Optional.of(clientService.updateClientsEmail(id, email));
            return new ResponseEntity<>(client1.get(), HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteClient(@PathVariable("id") long id){
        try{
            clientService.deleteClient(id);
            return new ResponseEntity<>("Client with id: " + id + " successfully deleted", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
