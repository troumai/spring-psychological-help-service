package kz.iitu.itse1903.abimoldayeva.service;

import kz.iitu.itse1903.abimoldayeva.database.Client;
import kz.iitu.itse1903.abimoldayeva.database.Therapist;
import kz.iitu.itse1903.abimoldayeva.database.TherapySession;
import kz.iitu.itse1903.abimoldayeva.repository.ClientRepository;
import kz.iitu.itse1903.abimoldayeva.repository.TherapistRepository;
import kz.iitu.itse1903.abimoldayeva.repository.TherapySessionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TherapySessionServiceTest {
    @Mock
    TherapySessionRepository therapySessionRepository;
    @Mock
    ClientRepository clientRepository;
    @Mock
    TherapistRepository therapistRepository;
    @InjectMocks
    TherapySessionService therapySessionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllTherapySessions() {
        List<TherapySession> therapySessionList = new ArrayList<>();
        TherapySession therapySession = new TherapySession(1L, LocalDate.parse("2022-12-31"), LocalTime.parse("12:41"));
        therapySessionList.add(therapySession);

        when(therapySessionRepository.findAll()).thenReturn(therapySessionList);

        List<TherapySession> result = therapySessionService.getAllTherapySessions();

        Assertions.assertEquals(therapySessionList, result);
        verify(therapySessionRepository).findAll();
    }

    @Test
    void testGetTherapySessionById() {
        TherapySession therapySession = new TherapySession(1L, LocalDate.parse("2022-12-31"), LocalTime.parse("12:41"));
        when(therapySessionRepository.getById(therapySession.getId())).thenReturn(therapySession);

        TherapySession result = therapySessionService.getTherapySessionById(therapySession.getId());
        Assertions.assertEquals(therapySession, result);
    }

    @Test
    void testSaveTherapySession() {
        TherapySession therapySession = new TherapySession(1L, LocalDate.parse("2022-12-31"), LocalTime.parse("12:41"));
        Client client = new Client();
        Therapist therapist = new Therapist();

        client.setId(1L);
        therapist.setId(1L);

        therapySession.setTherapist(therapist);
        therapySession.setClient(client);

        given(therapistRepository.getById(therapist.getId())).willReturn(therapist);
        given(clientRepository.findById(client.getId())).willReturn(Optional.of(client));

        when(therapySessionRepository.saveAndFlush(ArgumentMatchers.any(TherapySession.class))).thenReturn(therapySession);

        TherapySession therapySession1 = therapySessionService.saveTherapySession(client.getId(), therapist.getId(), therapySession);

        Assertions.assertEquals(therapySession, therapySession1);

    }

    @Test
    void testGetTherapySessionByDate() {
        List<TherapySession> expected = new ArrayList<>();
        TherapySession therapySession = new TherapySession(LocalDate.parse("2022-12-31"), LocalTime.parse("12:41"));
        expected.add(therapySession);
        when(therapySessionRepository.findAll().stream()
                .filter(therapySession1 -> therapySession1.getSessionDate().equals(therapySession.getSessionDate()))
                .collect(Collectors.toList())).thenReturn(expected);

        Optional<List<TherapySession>> actual = therapySessionService.getTherapySessionByDate(therapySession.getSessionDate());

        Assertions.assertEquals(expected, actual.get());
    }

    @Test
    void testUpdateTherapySessionDate() {
        TherapySession therapySession = new TherapySession();
        therapySession.setId(90L);
        therapySession.setSessionDate(LocalDate.parse("2022-12-30"));
        therapySession.setSessionTime(LocalTime.parse("20:13"));
        LocalDate newDate = LocalDate.parse("2022-04-21");

        given(therapySessionRepository.getById(therapySession.getId())).willReturn(therapySession);
        therapySession.setSessionDate(newDate);
        when(therapySessionRepository.saveAndFlush(ArgumentMatchers.any(TherapySession.class))).thenReturn(therapySession);

        TherapySession therapySession1 = therapySessionService.updateTherapySessionDate(therapySession.getId(), newDate);

        verify(therapySessionRepository).saveAndFlush(therapySession1);
        verify(therapySessionRepository).getById(therapySession.getId());
    }

    @Test
    void testDeleteTherapySession() {
        TherapySession therapySession = new TherapySession(1L, LocalDate.parse("2022-12-31"), LocalTime.parse("12:41"));
        when(therapySessionRepository.findById(therapySession.getId())).thenReturn(Optional.of(therapySession));
        therapySessionService.deleteTherapySession(therapySession.getId());
        verify(therapySessionRepository).deleteById(therapySession.getId());
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme