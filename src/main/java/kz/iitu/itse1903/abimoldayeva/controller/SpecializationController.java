package kz.iitu.itse1903.abimoldayeva.controller;

import kz.iitu.itse1903.abimoldayeva.database.Specialization;
import kz.iitu.itse1903.abimoldayeva.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/specialization")
public class SpecializationController {
    private final KafkaProducerService producer;

    @PostMapping("/create")
    public void createMessage(@RequestBody Specialization specialization){
        producer.sendMessage(specialization);
    }

}
