package ro.fasttrackit.curs13;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ro.fasttrackit.curs13.repository.CountryRepository;
import ro.fasttrackit.curs13.service.api.CountryApi;
import ro.fasttrackit.curs13.service.api.model.CountryApiModel;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {Curs13CodeApplication.class, TestConfig.class})
@ActiveProfiles({"test"})
class Curs13CodeApplicationTests {
    @Autowired
    MockMvc mvc;
    @Autowired
    CountryRepository repository;
    @Autowired
    CountryApi countryApi;

    @Test
    void contextLoads() {
    }

    @Test
    void fullFlowTest() throws Exception {
        doReturn(Optional.of(new CountryApiModel("Romania", "Bucharest"))).when(countryApi).getCountryByName(anyString());

        mvc.perform(post("/countries")
                        .with(request -> {
                            request.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                            request.setContent("""
                                    {
                                        "name" : "Romania"
                                    }
                                    """.getBytes());
                            return request;
                        }))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Romania")))
                .andExpect(jsonPath("$.capital", is("Bucharest")));

    }
}
