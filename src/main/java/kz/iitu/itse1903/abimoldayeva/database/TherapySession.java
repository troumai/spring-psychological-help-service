package kz.iitu.itse1903.abimoldayeva.database;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

//@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TherapySession implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "therapy_session_id")
    private Long id;
    private Long client_id;
    private Long therapist_id;

//    @ManyToOne
//    @JoinColumn(name = "client_id")
//    private Client client;

//    @ManyToOne
//    @JoinColumn(name = "therapist_id")
//    private Therapist therapist;

    private LocalDate sessionDate;
    private LocalTime sessionTime;

    public static List<TherapySession> getTherapySessions(){
        TherapySession therapySession1 = TherapySession.builder()
                .id(1L)
                .client_id(1L)
                .therapist_id(1L)
                .sessionDate(LocalDate.parse("2022-02-08"))
                .sessionTime(LocalTime.parse("19:00"))
                .build();
        TherapySession therapySession2 = TherapySession.builder()
                .id(2L)
                .client_id(2L)
                .therapist_id(2L)
                .sessionDate(LocalDate.parse("2022-02-10"))
                .sessionTime(LocalTime.parse("15:00"))
                .build();
        TherapySession therapySession3 = TherapySession.builder()
                .id(3L)
                .client_id(3L)
                .therapist_id(3L)
                .sessionDate(LocalDate.parse("2022-02-09"))
                .sessionTime(LocalTime.parse("16:00"))
                .build();
        TherapySession therapySession4 = TherapySession.builder()
                .id(4L)
                .client_id(4L)
                .therapist_id(4L)
                .sessionDate(LocalDate.parse("2022-02-10"))
                .sessionTime(LocalTime.parse("16:00"))
                .build();
        TherapySession therapySession5 = TherapySession.builder()
                .id(5L)
                .client_id(5L)
                .therapist_id(5L)
                .sessionDate(LocalDate.parse("2022-02-09"))
                .sessionTime(LocalTime.parse("11:00"))
                .build();
        TherapySession therapySession6 = TherapySession.builder()
                .id(6L)
                .client_id(6L)
                .therapist_id(6L)
                .sessionDate(LocalDate.parse("2022-02-09"))
                .sessionTime(LocalTime.parse("13:00"))
                .build();
        return Arrays.asList(therapySession1, therapySession2, therapySession3,
                therapySession4, therapySession5, therapySession6);
    }
}
