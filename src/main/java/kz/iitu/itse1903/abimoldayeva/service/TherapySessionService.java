package kz.iitu.itse1903.abimoldayeva.service;

import kz.iitu.itse1903.abimoldayeva.aop.ResourceNotFoundException;
import kz.iitu.itse1903.abimoldayeva.database.Client;
import kz.iitu.itse1903.abimoldayeva.database.Therapist;
import kz.iitu.itse1903.abimoldayeva.database.TherapySession;
import kz.iitu.itse1903.abimoldayeva.repository.ClientRepository;
import kz.iitu.itse1903.abimoldayeva.repository.TherapistRepository;
import kz.iitu.itse1903.abimoldayeva.repository.TherapySessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TherapySessionService {
    @Autowired
    private TherapySessionRepository therapySessionRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TherapistRepository therapistRepository;

    public List<TherapySession> getAllTherapySessions(){
        return therapySessionRepository.findAll();
    }

    public TherapySession getTherapySessionById(Long id){
        return therapySessionRepository.getById(id);
    }
    public TherapySession saveTherapySession(Long clientId, Long therapistId, TherapySession therapySession){
        Client client = clientRepository.findById(clientId).get();
        Therapist therapist = therapistRepository.getById(therapistId);

        therapySession.setClient(client);
        therapySession.setTherapist(therapist);

        therapySessionRepository.save(therapySession);

        return therapySession;
    }
}
