package kz.iitu.itse1903.abimoldayeva.controller;

import kz.iitu.itse1903.abimoldayeva.database.Client;
import kz.iitu.itse1903.abimoldayeva.exception.ResourceNotFoundException;
import kz.iitu.itse1903.abimoldayeva.service.ClientService;
import kz.iitu.itse1903.abimoldayeva.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/client")
@Slf4j
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final KafkaProducerService producer;

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public String getAllClients(Model model){
        List<Client> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Client client){
        return "add-client";
    }

    @PostMapping("/createClient")
    @ResponseBody
    public ModelAndView createClient(@Valid @ModelAttribute("clientEntity") Client client, BindingResult result, ModelMap model){
        if(result.hasErrors()){
            return new ModelAndView("redirect:/client/signup", model);
        }else {
            clientService.saveClient(new Client(
                    client.getFirstName(),
                    client.getLastName(),
                    client.getEmail(),
                    client.getCity(),
                    client.getAge(),
                    client.getSex()));
            return new ModelAndView("redirect:/client/list", model);
        }
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Client> getClientById(@PathVariable("id") long id) throws ResourceNotFoundException {
//        Optional<Client> client = clientService.getClientById(id);
//        return new ResponseEntity<>(client.get(), HttpStatus.OK);
//    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) throws ResourceNotFoundException {
        Optional<Client> client = clientService.getClientById(id);
        model.addAttribute("client", client.get());
        return "update-client";
    }

    @PostMapping("/update/{id}")
    @ResponseStatus
    public String updateClientEmail(@PathVariable("id") long id,
                                          @RequestParam("email") String email,  ModelMap model) throws ResourceNotFoundException {
        clientService.updateClientsEmail(id, email);
//        return new ModelAndView("redirect:/client/list", model);
        model.addAttribute("clients", clientService.getAllClients());
        return "index";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteClient(@PathVariable("id") long id, ModelMap model){
        clientService.deleteClient(id);
        model.addAttribute("clients", clientService.getAllClients());
        return "index";
    }
}
