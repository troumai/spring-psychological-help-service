package kz.iitu.itse1903.abimoldayeva.repository;

import kz.iitu.itse1903.abimoldayeva.database.TherapySession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TherapySessionRepository extends JpaRepository<TherapySession, Long> {
}
