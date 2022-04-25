package kz.iitu.itse1903.abimoldayeva.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.iitu.itse1903.abimoldayeva.database.Specialization;
import kz.iitu.itse1903.abimoldayeva.repository.SpecializationRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private SpecializationRepository specializationRepository;

    @SneakyThrows
    @KafkaListener(topics = "specialization", groupId = "group_id")
    public void consumeMessage(String specialization){
        Specialization specialization1 = new ObjectMapper().readValue(specialization, Specialization.class);
        specializationRepository.save(specialization1);
    }
}
