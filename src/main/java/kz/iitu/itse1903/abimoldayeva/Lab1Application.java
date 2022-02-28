package kz.iitu.itse1903.abimoldayeva;

import kz.iitu.itse1903.abimoldayeva.config.ClientConfig;
import kz.iitu.itse1903.abimoldayeva.config.TherapistConfig;
import kz.iitu.itse1903.abimoldayeva.config.TherapySessionConfig;
import kz.iitu.itse1903.abimoldayeva.database.Specialization;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableAutoConfiguration
@ComponentScan
@Import({ClientConfig.class, TherapistConfig.class, Specialization.class, TherapySessionConfig.class})
public class Lab1Application {
    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(Lab1Application.class, args);
    }

}
