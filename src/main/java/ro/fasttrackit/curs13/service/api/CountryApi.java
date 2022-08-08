package ro.fasttrackit.curs13.service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.fasttrackit.curs13.CountryApiConfig;
import ro.fasttrackit.curs13.service.api.model.CountryApiModel;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryApi {
    private final CountryApiConfig config;

    public Optional<CountryApiModel> getCountryByName(String countryName) {
        try {
            CountryApiModel forObject = new RestTemplate().exchange(config.url() + "/name/" + countryName,
                    HttpMethod.GET,
                    new HttpEntity<>(null),
                    new ParameterizedTypeReference<List<CountryApiModel>>() {
                    }).getBody().get(0);
            return Optional.ofNullable(forObject);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}
