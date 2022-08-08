package ro.fasttrackit.curs13.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs13.model.CountryEntity;
import ro.fasttrackit.curs13.repository.CountryRepository;
import ro.fasttrackit.curs13.service.api.CountryApi;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryApi countryApi;
    private final CountryRepository repository;

    public List<CountryEntity> getAll() {
        return repository.findAll();
    }

    public Optional<CountryEntity> addCountry(CountryEntity country) {
        return countryApi.getCountryByName(country.name())
                .map(apiCountry -> new CountryEntity(UUID.randomUUID().toString(), apiCountry.name(), apiCountry.capital()))
                .map(repository::save);
    }
}
