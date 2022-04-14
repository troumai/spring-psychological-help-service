package kz.iitu.itse1903.abimoldayeva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAutoConfiguration
@EnableCaching
@ComponentScan
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"kz.iitu.itse1903.abimoldayeva.repository"})
public class Lab1Application {
    private static ApplicationContext applicationContext;


    public static void main(String[] args) {

        applicationContext = SpringApplication.run(Lab1Application.class, args);
    }

}
