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
@Table(name = "client")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Scope(value = "singleton")
public class Client{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "client_id", nullable=false)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String city;
    private int age;
    private String sex;

    @OneToMany(mappedBy = "therapist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TherapySession> therapySessionList;

    public Client(String firstName, String lastName, String email, String city, int age, String sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
        this.age = age;
        this.sex = sex;
        this.therapySessionList = new ArrayList<>();
    }

    @PostConstruct
    public void doInit(){
        System.out.println("----------Client bean init-------------");
    }

    @PreDestroy
    public void doDestroy(){
        System.out.println("----------Client bean destroy-------------");
    }

    public void addTherapySession(TherapySession therapySession){
        therapySession.setClient(this);
        therapySessionList.add(therapySession);
    }

    public static List<Client> getClients(){
        Client client1 = Client.builder()
                .id(1L)
                .firstName("Tomiris")
                .lastName("Abimoldayeva")
                .email("abimoldayevat@gmail.com")
                .age(20)
                .city("Almaty")
                .sex("female")
                .build();
        Client client2 = Client.builder()
                .id(2L)
                .firstName("Chris")
                .lastName("Brown")
                .email("chris@gmail.com")
                .age(16)
                .city("Almaty")
                .sex("male")
                .build();

        Client client3 = Client.builder()
                .id(3L)
                .firstName("John")
                .lastName("Legend")
                .email("john@gmail.com")
                .age(40)
                .city("Tokyo")
                .sex("male")
                .build();

        Client client4 = Client.builder()
                .id(4L)
                .firstName("Lana")
                .lastName("Del Ray")
                .email("lana@gmail.com")
                .age(32)
                .city("Almaty")
                .sex("female")
                .build();

        Client client5 = Client.builder()
                .id(5L)
                .firstName("Selena")
                .lastName("Gomez")
                .email("selena@gmail.com")
                .age(29)
                .city("Tokyo")
                .sex("female")
                .build();

        Client client6 = Client.builder()
                .id(6L)
                .firstName("Kim")
                .lastName("Taehung")
                .email("taetae@gmail.com")
                .age(24)
                .city("Washington")
                .sex("male")
                .build();

        Client client7 = Client.builder()
                .id(7L)
                .firstName("Billie")
                .lastName("Eilish")
                .email("billie@gmail.com")
                .age(19)
                .city("Moscow")
                .sex("female")
                .build();

//        client1.addTherapySession(TherapySession.getTherapySessions().get(0));
//        client2.addTherapySession(TherapySession.getTherapySessions().get(1));
//        client3.addTherapySession(TherapySession.getTherapySessions().get(2));
//        client4.addTherapySession(TherapySession.getTherapySessions().get(3));
//        client5.addTherapySession(TherapySession.getTherapySessions().get(4));
//        client6.addTherapySession(TherapySession.getTherapySessions().get(5));
//        client7.addTherapySession(TherapySession.getTherapySessions().get(6));
//        client7.addTherapySession(TherapySession.getTherapySessions().get(7));

        return Arrays.asList(client1, client2, client3, client4, client5, client6, client7);
    }


}
