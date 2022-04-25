package kz.iitu.itse1903.abimoldayeva.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Scope(value="singleton")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Specialization extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specialization_id")
    private Long id;

    @Column(name = "specialization_name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "specialization", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Therapist> therapists = new HashSet<>();

//    public Specialization(String name) {
//        this.name = name;
//        therapists = new ArrayList<>();
//    }
//
//    public void addTherapist(Therapist therapist){
//        therapist.setSpecialization(this);
//        therapists.add(therapist);
//    }


    public Specialization(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @PostConstruct
    public void doInit(){
        System.out.println("----------Specialization bean init-------------");
    }

    @PreDestroy
    public void doDestroy(){
        System.out.println("----------Specialization bean destroy-------------");
    }

}
