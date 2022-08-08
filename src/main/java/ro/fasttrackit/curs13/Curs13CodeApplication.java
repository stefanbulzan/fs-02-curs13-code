package ro.fasttrackit.curs13;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(CountryApiConfig.class)
public class Curs13CodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(Curs13CodeApplication.class, args);
    }

}
