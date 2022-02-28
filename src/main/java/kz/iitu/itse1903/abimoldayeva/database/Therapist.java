package kz.iitu.itse1903.abimoldayeva.database;

import lombok.*;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.*;
import java.util.ArrayList;
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
public class Therapist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "therapist_id", nullable=false)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String city;
    private int age;
    private String sex;
    private int experience;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    @OneToMany(mappedBy = "therapist", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<TherapySession> therapySessionList;

    public Therapist(String firstName, String lastName, String email, String city, int age, String sex, int experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
        this.age = age;
        this.sex = sex;
        this.experience = experience;
        this.therapySessionList = new ArrayList<>();
    }

    public void addTherapySession(TherapySession therapySession){
        therapySession.setTherapist(this);
        therapySessionList.add(therapySession);
    }

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
                .age(26)
                .experience(5)
                .email("timothee@gmail.com")
                .sex("male")
                .city("Almaty")
                .build();

        Therapist therapist2 = Therapist.builder()
                .id(2L)
                .firstName("Zendaya")
                .lastName("Coleman")
                .age(23)
                .experience(2)
                .email("zendaya@gmail.com")
                .sex("female")
                .city("Moscow")
                .build();

        Therapist therapist3 = Therapist.builder()
                .id(3L)
                .firstName("Tom")
                .lastName("Holland")
                .age(45)
                .experience(16)
                .email("tom@gmail.com")
                .sex("male")
                .city("Tokyo")
                .build();

        Therapist therapist4 = Therapist.builder()
                .id(4L)
                .firstName("Elena")
                .lastName("Banderchuk")
                .age(66)
                .experience(45)
                .email("elena@gmail.com")
                .sex("female")
                .city("Almaty")
                .build();

        Therapist therapist5 = Therapist.builder()
                .id(5L)
                .firstName("Robert")
                .lastName("De Niro")
                .age(51)
                .experience(22)
                .email("robert@gmail.com")
                .sex("male")
                .city("Moscow")
                .build();

        Therapist therapist6 = Therapist.builder()
                .id(6L)
                .firstName("Marlon")
                .lastName("Brando")
                .age(23)
                .experience(1)
                .email("marlon@gmail.com")
                .sex("male")
                .city("Almaty")
                .build();

        Therapist therapist7 = Therapist.builder()
                .id(7L)
                .firstName("Maryl")
                .lastName("Streep")
                .age(33)
                .experience(10)
                .email("maryl@gmail.com")
                .sex("female")
                .city("Almaty")
                .build();

        Therapist therapist8 = Therapist.builder()
                .id(8L)
                .firstName("Tom")
                .lastName("Hanks")
                .age(32)
                .experience(12)
                .email("tom@gmail.com")
                .sex("male")
                .city("Almaty")
                .build();

//        therapist1.addTherapySession(TherapySession.getTherapySessions().get(0));
//        therapist2.addTherapySession(TherapySession.getTherapySessions().get(1));
//        therapist3.addTherapySession(TherapySession.getTherapySessions().get(2));
//        therapist4.addTherapySession(TherapySession.getTherapySessions().get(3));
//        therapist5.addTherapySession(TherapySession.getTherapySessions().get(4));
//        therapist6.addTherapySession(TherapySession.getTherapySessions().get(5));
//        therapist7.addTherapySession(TherapySession.getTherapySessions().get(6));
//        therapist8.addTherapySession(TherapySession.getTherapySessions().get(7));

        return Arrays.asList(therapist1, therapist2, therapist3, therapist4, therapist5,
                therapist6, therapist7, therapist8);
    }
}
