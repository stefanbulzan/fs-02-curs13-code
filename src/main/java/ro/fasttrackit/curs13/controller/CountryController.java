package ro.fasttrackit.curs13.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.curs13.model.CountryEntity;
import ro.fasttrackit.curs13.service.CountryService;

import java.util.List;

@RestController
@RequestMapping("countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService service;

    @GetMapping
    List<CountryEntity> getAll() {
        return service.getAll();
    }

    @PostMapping
    CountryEntity addOne(@RequestBody CountryEntity country) {
        return service.addCountry(country)
                .orElse(null);
    }
}
