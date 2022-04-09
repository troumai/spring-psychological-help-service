package kz.iitu.itse1903.abimoldayeva;

import kz.iitu.itse1903.abimoldayeva.config.ClientConfig;
import kz.iitu.itse1903.abimoldayeva.config.SpecializationConfig;
import kz.iitu.itse1903.abimoldayeva.config.TherapistConfig;
import kz.iitu.itse1903.abimoldayeva.config.TherapySessionConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration
@EnableCaching
@ComponentScan
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"kz.iitu.itse1903.abimoldayeva.repository"})
@Import({TherapySessionConfig.class, TherapistConfig.class, SpecializationConfig.class, ClientConfig.class,})
public class Lab1Application {
    private static ApplicationContext applicationContext;


    public static void main(String[] args) {

        applicationContext = SpringApplication.run(Lab1Application.class, args);
    }

}
