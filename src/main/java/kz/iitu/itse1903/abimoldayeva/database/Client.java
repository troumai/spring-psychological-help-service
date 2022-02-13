package kz.iitu.itse1903.abimoldayeva.database;

import lombok.*;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Scope(value = "singleton")
public class Client{
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String city;
    private int age;
    private String sex;

    @PostConstruct
    public void doInit(){
        System.out.println("----------Client bean init-------------");
    }

    @PreDestroy
    public void doDestroy(){
        System.out.println("----------Client bean destroy-------------");
    }

    public static List<Client> getClients(){
        Client client1 = Client.builder()
                .id(1L)
                .firstName("Tomiris")
                .lastName("Abimoldayeva")
                .phone("87780058066")
                .email("abimoldayevat@gmail.com")
                .age(20)
                .sex("female")
                .build();

        Client client2 = Client.builder()
                .id(2L)
                .firstName("Chris")
                .lastName("Brown")
                .phone("87780058066")
                .email("chris@gmail.com")
                .age(16)
                .sex("male")
                .build();

        Client client3 = Client.builder()
                .id(3L)
                .firstName("John")
                .lastName("Legend")
                .phone("87780058066")
                .email("john@gmail.com")
                .age(40)
                .sex("male")
                .build();

        Client client4 = Client.builder()
                .id(4L)
                .firstName("Lana")
                .lastName("Del Ray")
                .phone("87780058066")
                .email("lana@gmail.com")
                .age(32)
                .sex("female")
                .build();

        Client client5 = Client.builder()
                .id(5L)
                .firstName("Selena")
                .lastName("Gomez")
                .phone("87780058066")
                .email("selena@gmail.com")
                .age(29)
                .sex("female")
                .build();

        Client client6 = Client.builder()
                .id(6L)
                .firstName("Kim")
                .lastName("Taehung")
                .phone("87780058066")
                .email("taetae@gmail.com")
                .age(24)
                .sex("male")
                .build();

        Client client7 = Client.builder()
                .id(7L)
                .firstName("Billie")
                .lastName("Eilish")
                .phone("87780058066")
                .email("billie@gmail.com")
                .age(19)
                .sex("female")
                .build();

        return Arrays.asList(client1, client2, client3, client4, client5, client6, client7);
    }


}
