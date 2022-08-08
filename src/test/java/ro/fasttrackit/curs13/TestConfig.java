package ro.fasttrackit.curs13;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.fasttrackit.curs13.service.api.CountryApi;

@Configuration
public class TestConfig {
    @Bean
    CountryApi countryApi() {
        return Mockito.mock(CountryApi.class);
    }
}
