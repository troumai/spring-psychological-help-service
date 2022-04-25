package kz.iitu.itse1903.abimoldayeva.database;

import kz.iitu.itse1903.abimoldayeva.validation.EmailCheck;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Scope(value = "singleton")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Therapist extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "therapist_id")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String city;
    private int age;
    private String sex;
    private int experience;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;


    @OneToMany(mappedBy = "therapist", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
     private Set<TherapySession> therapySessionList = new HashSet<>();

//    public Therapist(String firstName, String lastName, String email, String city, int age, String sex, int experience, Specialization specialization) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.city = city;
//        this.age = age;
//        this.sex = sex;
//        this.experience = experience;
//        this.specialization = specialization;
//        therapySessionList = new ArrayList<>();
//    }
//
//    public void addSession(TherapySession therapySession){
//        therapySession.setTherapist(this);
//        therapySessionList.add(therapySession);
//    }


    public Therapist(Long id, String firstName, String lastName, String email, String city, int age, String sex, int experience, Specialization specialization) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
        this.age = age;
        this.sex = sex;
        this.experience = experience;
        this.specialization = specialization;
    }

    @PostConstruct
    public void doInit(){
        System.out.println("----------Therapist bean init-------------");
    }

    @PreDestroy
    public void doDestroy(){
        System.out.println("----------Therapist bean destroy-------------");
    }


}
