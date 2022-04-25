package kz.iitu.itse1903.abimoldayeva.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.iitu.itse1903.abimoldayeva.database.Specialization;
import lombok.SneakyThrows;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @SneakyThrows
    public void sendMessage(Specialization specialization){
        kafkaTemplate.send("specialization", new ObjectMapper().writeValueAsString(specialization));
    }

    @Bean
    public NewTopic createTopic(){
        return new NewTopic("specialization", 1, (short) 1);
    }
}
