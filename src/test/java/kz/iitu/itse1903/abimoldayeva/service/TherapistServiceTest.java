//package kz.iitu.itse1903.abimoldayeva.service;
//
//import kz.iitu.itse1903.abimoldayeva.database.Therapist;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import static org.mockito.Mockito.when;
//
//class TherapistServiceTest {
//    @Mock
//    TherapistRepositoryImpl therapistRepository;
//
//    @Mock
//    Therapist therapist;
//
//    @InjectMocks
//    TherapistService therapistService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    private Therapist therapist1 = Therapist.builder()
//            .id(1L)
//            .firstName("Timothée")
//            .lastName("Chalamet")
//            .age(26)
//            .experience(5)
//            .email("timothee@gmail.com")
//            .sex("male")
//            .specialization_id(1L)
//            .city("Almaty")
//            .build();
//
//    @Test
//    void getAllTherapistsTest() {
//        when(therapistRepository.findAllTherapists()).thenReturn(Arrays.asList(therapist1));
//
//        List<Therapist> result = therapistService.getAllTherapists();
//        Assertions.assertEquals(Arrays.asList(therapist1).toString(), result.toString());
//    }
//
//    @Test
//    void getTherapistByIdTest() {
//        when(therapistRepository.findTherapistById(1L)).thenReturn(Optional.of(therapist1));
//
//        Therapist result = therapistService.getTherapistById(1L).get();
//        Assertions.assertEquals(Therapist.getTherapists().get(0).toString(), result.toString());
//    }
//
//    @Test
//    void getTherapistsByExperienceTest() {
//        when(therapistRepository.findTherapistByExperience(10)).thenReturn(Optional.of(Therapist.getTherapists().stream()
//                .filter(therapist -> therapist.getExperience() >= 10).collect(Collectors.toList())));
//
//        Optional<List<Therapist>> result = therapistService.getTherapistsByExperience(10);
//        Assertions.assertEquals(Therapist.getTherapists().stream()
//                .filter(therapist -> therapist.getExperience() >= 10).collect(Collectors.toList()).toString(), result.get().toString());
//    }
//}