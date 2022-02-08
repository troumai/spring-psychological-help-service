package kz.iitu.itse1903.abimoldayeva.service;

import kz.iitu.itse1903.abimoldayeva.database.TherapySession;
import kz.iitu.itse1903.abimoldayeva.repository.TherapySessionRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TherapySessionService {
    @Autowired
    private TherapySessionRepositoryImpl therapySessionRepositoryImpl;

    public List<TherapySession> getAllTherapySessions(){
        return therapySessionRepositoryImpl.findAllTherapySessions();
    }

    public Optional<List<TherapySession>> getTherapySessionByDate(LocalDate date){
        return therapySessionRepositoryImpl.getTherapySessionByDate(date);
    }
}
