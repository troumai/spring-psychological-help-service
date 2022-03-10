package kz.iitu.itse1903.abimoldayeva.database;

import lombok.*;
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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Scope(value = "singleton")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String city;
    private int age;
    private String sex;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<TherapySession> therapySessions = new HashSet<>();

//    public Client(String firstName, String lastName, String phone, String email, String city, int age, String sex) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.phone = phone;
//        this.email = email;
//        this.city = city;
//        this.age = age;
//        this.sex = sex;
//        therapySessions = new ArrayList<>();
//    }
//
//    public void addTherapySession(TherapySession therapySession){
//        therapySession.setClient(this);
//        therapySessions.add(therapySession);
//    }

    @PostConstruct
    public void doInit(){
        System.out.println("----------Client bean init-------------");
    }

    @PreDestroy
    public void doDestroy(){
        System.out.println("----------Client bean destroy-------------");
    }

}
