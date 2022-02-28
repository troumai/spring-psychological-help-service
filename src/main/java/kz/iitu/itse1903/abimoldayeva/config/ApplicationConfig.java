package kz.iitu.itse1903.abimoldayeva.config;

import kz.iitu.itse1903.abimoldayeva.database.Client;
import kz.iitu.itse1903.abimoldayeva.database.Specialization;
import kz.iitu.itse1903.abimoldayeva.database.Therapist;
import kz.iitu.itse1903.abimoldayeva.database.TherapySession;
import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

    @Bean(initMethod = "doInit", destroyMethod = "doDestroy", name = "client")
    @DependsOn("therapist")
    public Client client(){
        return new Client();
    }


    @Bean(initMethod = "doInit", destroyMethod = "doDestroy", name = "therapist")

    public Therapist therapist(){
        return new Therapist();
    }

    @Bean(initMethod = "doInit", destroyMethod = "doDestroy", name = "specialization")
    public Specialization specialization(){
        return new Specialization();
    }

    @Bean(initMethod = "doInit", destroyMethod = "doDestroy", name = "therapySession")
    public TherapySession therapySession(){
        return new TherapySession();
    }

}
