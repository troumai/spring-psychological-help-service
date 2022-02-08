package kz.iitu.itse1903.abimoldayeva.database;

import lombok.*;

import java.util.Arrays;
import java.util.List;

//@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Specialization {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "specialization_id")
    private Long id;
    private String name;

//    @OneToMany(mappedBy = "specialization", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Therapist> therapists;

    public Specialization(String name) {
        this.name = name;
//        therapists = new ArrayList<>();
    }

//    public void addTherapist(Therapist therapist){
//        therapist.setSpecialization(this);
//        therapists.add(therapist);
//    }

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

        return Arrays.asList(specialization1, specialization2, specialization3, specialization4,
                specialization5, specialization6, specialization7);
    }


}
