package kz.iitu.itse1903.abimoldayeva.database;

import lombok.*;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Scope(value = "singleton")
public class TherapySession implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "therapy_session_id", nullable=false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;

    private LocalDate sessionDate;
    private LocalTime sessionTime;

    public TherapySession(LocalDate sessionDate, LocalTime sessionTime) {
        this.sessionDate = sessionDate;
        this.sessionTime = sessionTime;
    }

    @PostConstruct
    public void doInit(){
        System.out.println("----------TherapySession bean init-------------");
    }

    @PreDestroy
    public void doDestroy(){
        System.out.println("----------TherapySession bean destroy-------------");
    }

    public static List<TherapySession> getTherapySessions(){
        TherapySession therapySession1 = TherapySession.builder()
                .id(1L)
                .sessionDate(LocalDate.parse("2022-02-08"))
                .sessionTime(LocalTime.parse("19:00"))
                .build();
        TherapySession therapySession2 = TherapySession.builder()
                .id(2L)
                .sessionDate(LocalDate.parse("2022-02-10"))
                .sessionTime(LocalTime.parse("15:00"))
                .build();
        TherapySession therapySession3 = TherapySession.builder()
                .id(3L)
                .sessionDate(LocalDate.parse("2022-02-09"))
                .sessionTime(LocalTime.parse("16:00"))
                .build();
        TherapySession therapySession4 = TherapySession.builder()
                .id(4L)
                .sessionDate(LocalDate.parse("2022-02-10"))
                .sessionTime(LocalTime.parse("16:00"))
                .build();
        TherapySession therapySession5 = TherapySession.builder()
                .id(5L)
                .sessionDate(LocalDate.parse("2022-02-09"))
                .sessionTime(LocalTime.parse("11:00"))
                .build();
        TherapySession therapySession6 = TherapySession.builder()
                .id(6L)
                .sessionDate(LocalDate.parse("2022-02-09"))
                .sessionTime(LocalTime.parse("13:00"))
                .build();
        TherapySession therapySession7 = TherapySession.builder()
                .id(7L)
                .sessionDate(LocalDate.parse("2022-02-10"))
                .sessionTime(LocalTime.parse("13:00"))
                .build();
        TherapySession therapySession8 = TherapySession.builder()
                .id(8L)
                .sessionDate(LocalDate.parse("2022-02-10"))
                .sessionTime(LocalTime.parse("17:00"))
                .build();
        return Arrays.asList(therapySession1, therapySession2, therapySession3,
                therapySession4, therapySession5, therapySession6, therapySession7, therapySession8);
    }
}
