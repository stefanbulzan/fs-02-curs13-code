package ro.fasttrackit.curs13.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ro.fasttrackit.curs13.Curs13CodeApplication;
import ro.fasttrackit.curs13.model.CountryEntity;
import ro.fasttrackit.curs13.service.CountryService;

import java.util.List;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = {Curs13CodeApplication.class, CountryControllerTest.TestConfig.class})
class CountryControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    CountryService countryService;

    @Test
    @DisplayName("GET /countries")
    void getAllCountries() throws Exception {
        Mockito.doReturn(List.of(new CountryEntity("1", "Romania", "Bucharest"))).when(countryService).getAll();

        mvc.perform(get("/countries"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            [
                                {
                                    "id": "1",
                                    "name" : "Romania",
                                    "capital": "Bucharest"
                                }
                            ]
                        """));

        verify(countryService, atLeastOnce()).getAll();
    }

    @Configuration
    static class TestConfig {
        @Bean
        CountryService countryService() {
            return Mockito.mock(CountryService.class);
        }
    }

}