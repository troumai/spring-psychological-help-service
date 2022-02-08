package kz.iitu.itse1903.abimoldayeva.repository;

import kz.iitu.itse1903.abimoldayeva.database.TherapySession;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TherapySessionRepository {
    List<TherapySession> findAllTherapySessions();
    Optional<List<TherapySession>> getTherapySessionByDate(LocalDate date);
}
