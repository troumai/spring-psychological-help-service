package kz.iitu.itse1903.abimoldayeva.service;

import kz.iitu.itse1903.abimoldayeva.database.Client;
import kz.iitu.itse1903.abimoldayeva.database.TherapySession;
import kz.iitu.itse1903.abimoldayeva.repository.TherapySessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TherapySessionService {
    @Autowired
    private TherapySessionRepository therapySessionRepository;

    List<TherapySession> therapySessionList = TherapySession.getTherapySessions();

    public void createSessionsFromList(){
        therapySessionList.forEach(
                therapySession -> therapySessionRepository.save(therapySession));
    }

    public List<TherapySession> getAllTherapySessions(){
        return therapySessionRepository.findAll();
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
        return therapySessionRepository.save(therapySession);
    }

    public void deleteTherapySession(Long id){
        therapySessionRepository.deleteById(id);
    }
}
