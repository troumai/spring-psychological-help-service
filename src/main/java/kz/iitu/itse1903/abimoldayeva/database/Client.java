package kz.iitu.itse1903.abimoldayeva.database;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Scope(value = "singleton")
@NamedQuery(name = "Client.findByEmail",
        query = "select c from Client c where c.email = ?1")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Client extends Auditable<String> implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @Pattern(message = "Bad formed name: ${validatedValue}",
            regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]+\\')?[A-Z][a-z]*)))*$")
    @Length(min = 2)
    @NotNull(message = "user firstname shouldn't be null")
    private String firstName;

    @Pattern(message = "Bad formed lastname: ${validatedValue}",
            regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]+\\')?[A-Z][a-z]*)))*$")
    @Length(min = 2)
    @NotNull(message = "user lastname shouldn't be null")
    private String lastName;

    @Email
    @NotNull(message = "user email shouldn't be null")
    private String email;

    private String city;

    @NotNull(message = "user age shouldn't be null")
    @Min(5)
    @Max(100)
    private int age;

    @NotNull(message = "user sex shouldn't be null")
    private String sex;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<TherapySession> therapySessions = new HashSet<>();

    public Client(String firstName, String lastName, String email, String city, int age, String sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
        this.age = age;
        this.sex = sex;
    }
}
