package kz.iitu.itse1903.abimoldayeva.config;

import kz.iitu.itse1903.abimoldayeva.converter.StringToInt;
import kz.iitu.itse1903.abimoldayeva.database.Client;
import kz.iitu.itse1903.abimoldayeva.database.Specialization;
import kz.iitu.itse1903.abimoldayeva.database.Therapist;
import kz.iitu.itse1903.abimoldayeva.database.TherapySession;
import kz.iitu.itse1903.abimoldayeva.formatter.GenderFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.format.Formatter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;

@Configuration
@PropertySource("classpath:application.properties")
@EnableScheduling
@EnableAsync
public class ApplicationConfig {

    @Bean(initMethod = "doInit", destroyMethod = "doDestroy", name = "client")
    public Client client(){
        return new Client();
    }

    @Bean(initMethod = "doInit", destroyMethod = "doDestroy", name = "therapist")
    public Therapist therapist(){
        return new Therapist();
    }

    @Bean(initMethod = "doInit", destroyMethod = "doDestroy", name = "specialization")
    @DependsOn("client")
    public Specialization specialization(){
        return new Specialization();
    }

    @Bean(initMethod = "doInit", destroyMethod = "doDestroy", name = "therapySession")
    public TherapySession therapySession(){
        return new TherapySession();
    }

    @Bean
    public ConversionService conversionService(){
        DefaultConversionService service = new DefaultConversionService();
        service.addConverter(new StringToInt());
        return service;
    }

    @Bean
    public FormattingConversionServiceFactoryBean formattingConversionServiceFactoryBean(){
        FormattingConversionServiceFactoryBean bean = new FormattingConversionServiceFactoryBean();
        Set<Formatter> formatters = new HashSet<>();
        formatters.add(new GenderFormatter());
        bean.setFormatters(formatters);
        return bean;
    }

    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }

}
