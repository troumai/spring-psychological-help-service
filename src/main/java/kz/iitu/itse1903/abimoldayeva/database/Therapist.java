package kz.iitu.itse1903.abimoldayeva.database;

import lombok.*;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;

//@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Scope(value = "singleton")
public class Therapist {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "therapist_id")
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String city;
    private int age;
    private String sex;
    private String about;
    private int experience;
    private String degree;
    private Long specialization_id;

//    @ManyToOne
//    @JoinColumn(name = "specialization_id")
//    private Specialization specialization;
//
//    @OneToMany(mappedBy = "therapist", cascade = CascadeType.ALL, orphanRemoval = true)
     //private List<TherapySession> therapySessionList;
//

//    public void addSession(TherapySession therapySession){
//        therapySession.setTherapist(this);
//        therapySessionList.add(therapySession);
//    }

    @PostConstruct
    public void doInit(){
        System.out.println("----------Therapist bean init-------------");
    }

    @PreDestroy
    public void doDestroy(){
        System.out.println("----------Therapist bean destroy-------------");
    }

    public static List<Therapist> getTherapists(){
        Therapist therapist1 = Therapist.builder()
                .id(1L)
                .firstName("Timothée")
                .lastName("Chalamet")
                .phone("877777777777")
                .age(26)
                .about("I help overcome depression, anxiety, panic disorder, apathy, stress. " +
                        "I work in Gestalt Therapy research and usually use a variety of therapies.")
                .experience(5)
                .degree("bachelor")
                .email("timothee@gmail.com")
                .sex("male")
                .specialization_id(1L)
                .city("Almaty")
                .build();

        Therapist therapist2 = Therapist.builder()
                .id(2L)
                .firstName("Zendaya")
                .lastName("Coleman")
                .phone("877777777777")
                .age(23)
                .about("I help overcome depression, anxiety, panic disorder, apathy, stress. " +
                        "I work in Gestalt Therapy research and usually use a variety of therapies.")
                .experience(2)
                .degree("bachelor")
                .email("zendaya@gmail.com")
                .sex("female")
                .specialization_id(2L)
                .city("Moscow")
                .build();

        Therapist therapist3 = Therapist.builder()
                .id(3L)
                .firstName("Tom")
                .lastName("Holland")
                .phone("877777777777")
                .age(45)
                .about("I help overcome depression, anxiety, panic disorder, apathy, stress. " +
                        "I work in Gestalt Therapy research and usually use a variety of therapies.")
                .experience(16)
                .degree("master")
                .email("tom@gmail.com")
                .sex("male")
                .specialization_id(3L)
                .city("Tokyo")
                .build();

        Therapist therapist4 = Therapist.builder()
                .id(4L)
                .firstName("Elena")
                .lastName("Banderchuk")
                .phone("877777777777")
                .age(66)
                .about("I help overcome depression, anxiety, panic disorder, apathy, stress. " +
                        "I work in Gestalt Therapy research and usually use a variety of therapies.")
                .experience(45)
                .degree("bachelor")
                .email("elena@gmail.com")
                .sex("female")
                .specialization_id(4L)
                .city("Almaty")
                .build();

        Therapist therapist5 = Therapist.builder()
                .id(5L)
                .firstName("Robert")
                .lastName("De Niro")
                .phone("877777777777")
                .age(51)
                .about("I help overcome depression, anxiety, panic disorder, apathy, stress. " +
                        "I work in Gestalt Therapy research and usually use a variety of therapies.")
                .experience(22)
                .degree("master")
                .email("robert@gmail.com")
                .sex("male")
                .specialization_id(5L)
                .city("Moscow")
                .build();

        Therapist therapist6 = Therapist.builder()
                .id(6L)
                .firstName("Marlon")
                .lastName("Brando")
                .phone("877777777777")
                .age(23)
                .about("I help overcome depression, anxiety, panic disorder, apathy, stress. " +
                        "I work in Gestalt Therapy research and usually use a variety of therapies.")
                .experience(1)
                .degree("bachelor")
                .email("marlon@gmail.com")
                .sex("male")
                .specialization_id(6L)
                .city("Almaty")
                .build();

        Therapist therapist7 = Therapist.builder()
                .id(7L)
                .firstName("Maryl")
                .lastName("Streep")
                .phone("877777777777")
                .age(33)
                .about("I help overcome depression, anxiety, panic disorder, apathy, stress. " +
                        "I work in Gestalt Therapy research and usually use a variety of therapies.")
                .experience(10)
                .degree("master")
                .email("maryl@gmail.com")
                .sex("female")
                .specialization_id(7L)
                .city("Almaty")
                .build();

        Therapist therapist8 = Therapist.builder()
                .id(8L)
                .firstName("Tom")
                .lastName("Hanks")
                .phone("877777777777")
                .age(32)
                .about("I help overcome depression, anxiety, panic disorder, apathy, stress. " +
                        "I work in Gestalt Therapy research and usually use a variety of therapies.")
                .experience(12)
                .degree("bachelor")
                .email("tom@gmail.com")
                .sex("male")
                .specialization_id(1L)
                .city("Almaty")
                .build();

        return Arrays.asList(therapist1, therapist2, therapist3, therapist4, therapist5,
                therapist6, therapist7, therapist8);
    }
}
