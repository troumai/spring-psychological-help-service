package kz.iitu.itse1903.abimoldayeva.database;

import lombok.*;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

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
    @Column(name = "therapy_session_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
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



}
