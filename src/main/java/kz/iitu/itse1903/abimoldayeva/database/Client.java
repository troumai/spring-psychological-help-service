package kz.iitu.itse1903.abimoldayeva.database;

import kz.iitu.itse1903.abimoldayeva.validation.EmailCheck;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
@NamedQuery(name = "Client.findByEmail",
        query = "select c from Client c where c.email = ?1")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@EmailCheck
@Validated
public class Client extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @Pattern(message = "Bad formed name: ${validatedValue}",
            regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]+\\')?[A-Z][a-z]*)))*$")
    @Length(min = 2)
    @NotNull
    private String firstName;

    @Pattern(message = "Bad formed lastname: ${validatedValue}",
            regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]+\\')?[A-Z][a-z]*)))*$")
    @Length(min = 2)
    @NotNull
    private String lastName;

    private String email;

    private String city;

    @NotNull
    private int age;

    @NotNull
    private String sex;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<TherapySession> therapySessions = new HashSet<>();

    @AssertTrue(message = "Error! Client should have email and city defined")
    public boolean isClientFemale(){
        boolean result = true;

        if(sex!=null &&
                (sex.equals("female") &&
                        email==null || city==null))
            result = false;

        return result;
    }


    @PostConstruct
    public void doInit(){
        System.out.println("----------Client bean init-------------");
    }

    @PreDestroy
    public void doDestroy(){
        System.out.println("----------Client bean destroy-------------");
    }

}
