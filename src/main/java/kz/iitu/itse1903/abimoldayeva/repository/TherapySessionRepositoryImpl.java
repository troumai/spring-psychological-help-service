package kz.iitu.itse1903.abimoldayeva.repository;

import kz.iitu.itse1903.abimoldayeva.database.TherapySession;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TherapySessionRepositoryImpl implements TherapySessionRepository{
    List<TherapySession> therapySessionList = TherapySession.getTherapySessions();

    @Override
    public List<TherapySession> findAllTherapySessions() {
        return therapySessionList;
    }

    @Override
    public Optional<List<TherapySession>> getTherapySessionByDate(LocalDate date) {
        List<TherapySession> filteredTherapySessionList = therapySessionList.stream()
                .filter(therapySession -> therapySession.getSessionDate().equals(date))
                .collect(Collectors.toList());
        return Optional.of(filteredTherapySessionList);
    }
}
