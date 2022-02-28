package kz.iitu.itse1903.abimoldayeva.database;

import lombok.*;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Scope(value = "singleton")
public class Specialization implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specialization_id", nullable=false)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "specialization", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Therapist> therapists;

    public Specialization(String name) {
        this.name = name;
        therapists = new ArrayList<>();
    }

    public void addTherapist(Therapist therapist){
        therapist.setSpecialization(this);
        therapists.add(therapist);
    }

    @PostConstruct
    public void doInit(){
        System.out.println("----------Specialization bean init-------------");
    }

    @PreDestroy
    public void doDestroy(){
        System.out.println("----------Specialization bean destroy-------------");
    }

    public static List<Specialization> getSpecialization(){
        Specialization specialization1 = Specialization.builder()
                .id(1L)
                .name("Family")
                .build();

        Specialization specialization2 = Specialization.builder()
                .id(2L)
                .name("Stress")
                .build();

        Specialization specialization3 = Specialization.builder()
                .id(3L)
                .name("Burnout")
                .build();

        Specialization specialization4 = Specialization.builder()
                .id(4L)
                .name("Relationships")
                .build();

        Specialization specialization5 = Specialization.builder()
                .id(5L)
                .name("Motivation")
                .build();

        Specialization specialization6 = Specialization.builder()
                .id(6L)
                .name("Depression")
                .build();

        Specialization specialization7 = Specialization.builder()
                .id(7L)
                .name("Anxiety")
                .build();
//
//        specialization1.addTherapist(Therapist.getTherapists().get(0));
//        specialization2.addTherapist(Therapist.getTherapists().get(1));
//        specialization3.addTherapist(Therapist.getTherapists().get(2));
//        specialization4.addTherapist(Therapist.getTherapists().get(3));
//        specialization5.addTherapist(Therapist.getTherapists().get(4));
//        specialization6.addTherapist(Therapist.getTherapists().get(5));
//        specialization7.addTherapist(Therapist.getTherapists().get(6));
//        specialization1.addTherapist(Therapist.getTherapists().get(7));

        return Arrays.asList(specialization1, specialization2, specialization3, specialization4,
                specialization5, specialization6, specialization7);
    }


}
