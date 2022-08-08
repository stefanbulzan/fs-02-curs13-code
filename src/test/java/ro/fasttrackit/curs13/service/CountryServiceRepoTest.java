package ro.fasttrackit.curs13.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ro.fasttrackit.curs13.model.CountryEntity;
import ro.fasttrackit.curs13.repository.CountryRepository;
import ro.fasttrackit.curs13.service.api.CountryApi;
import ro.fasttrackit.curs13.service.api.model.CountryApiModel;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@DataMongoTest
class CountryServiceRepoTest {
    @Autowired
    CountryRepository repository;
    CountryService countryService;
    CountryApi countryApiMock;

    @BeforeEach
    void setup() {
        repository.deleteAll();
        countryApiMock = Mockito.mock(CountryApi.class);
        countryService = new CountryService(countryApiMock, repository);
    }

    @Test
    @DisplayName("WHEN a country is added THEN the repository has 1 country")
    void testAddCountry() {
        doReturn(Optional.of(new CountryApiModel("Belgia", "Bruxelles")))
                .when(countryApiMock).getCountryByName(Mockito.anyString());

        Optional<CountryEntity> result = countryService.addCountry(new CountryEntity(null, "Belgia", null));

        CountryEntity expected = new CountryEntity(result.get().id(), "Belgia", "Bruxelles");
        assertThat(result).isNotEmpty().contains(expected);
        assertThat(repository.count()).isOne();
        assertThat(repository.findById(result.get().id()))
                .contains(expected);


    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

}