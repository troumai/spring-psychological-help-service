package kz.iitu.itse1903.abimoldayeva.config;

import kz.iitu.itse1903.abimoldayeva.database.TherapySession;
import kz.iitu.itse1903.abimoldayeva.service.ClientService;
import kz.iitu.itse1903.abimoldayeva.service.TherapistService;
import kz.iitu.itse1903.abimoldayeva.service.TherapySessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Configuration
@Lazy
public class TherapySessionConfig {
    @Autowired
    private TherapySessionService therapySessionService;

    @Autowired
    private TherapistService therapistService;

    @Autowired
    private ClientService clientService;

    @Bean
    public void createTherapySession(){
        therapySessionService.createSessionsFromList();
    }

    @Bean
    public void getAllTherapySessions(){
        therapySessionService.getAllTherapySessions().stream().forEach(
                therapySession -> System.out.println(therapySession.getId()+" "+therapySession.getSessionDate()));
    }

    @Bean
    public void getTherapySessionByDate(){
        Optional<List<TherapySession>> therapySessionList = therapySessionService
                .getTherapySessionByDate(LocalDate.parse("2022-02-08"));
        therapySessionList.get().forEach(therapySession -> System.out.println(
                therapySession.getId() + " " +
                therapySession.getSessionTime()));
    }

    @Bean
    @Lazy
    public void updateTherapySessionDate(){
        System.out.println("\n------------3.Update therapy session date-----------");
        System.out.println("Before updating: " + therapySessionService.getTherapySessionByDate(LocalDate.parse("2022-02-10")).get());
        therapySessionService.updateTherapySessionDate(4L, LocalDate.parse("2022-02-11"));
        System.out.println("After updating: " + therapySessionService.getTherapySessionByDate(LocalDate.parse("2022-02-11")).get());
    }
}


/*
*   @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, Book... books) {
        this.name = name;
        this.books = Stream.of(books).collect(Collectors.toList());
        this.books.forEach(x -> x.setCategory(this));
    }

    public Category(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }
* */
