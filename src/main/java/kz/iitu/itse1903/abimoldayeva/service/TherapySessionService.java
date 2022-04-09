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

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
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

        therapySessionRepository.saveAndFlush(therapySession);

        return therapySession;
    }

    public Optional<List<TherapySession>> getTherapySessionByDate(LocalDate date){
        List<TherapySession> therapySessionList = getAllTherapySessions().stream()
                .filter(therapySession -> therapySession.getSessionDate().equals(date))
                .collect(Collectors.toList());
        return Optional.of(therapySessionList);
    }

    public TherapySession updateTherapySessionDate(Long id, LocalDate date){
        TherapySession therapySession = therapySessionRepository.getById(id);
        therapySession.setSessionDate(date);
        return therapySessionRepository.saveAndFlush(therapySession);
    }

    public void deleteTherapySession(Long id){
        therapySessionRepository.deleteById(id);
    }
}
