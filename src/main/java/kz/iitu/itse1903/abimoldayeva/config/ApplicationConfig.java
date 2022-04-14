package kz.iitu.itse1903.abimoldayeva.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@PropertySource("classpath:application.properties")
@EnableScheduling
@EnableAsync
public class ApplicationConfig {

}
