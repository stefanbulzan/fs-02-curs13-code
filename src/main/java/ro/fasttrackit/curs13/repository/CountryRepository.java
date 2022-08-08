package ro.fasttrackit.curs13.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.curs13.model.CountryEntity;

public interface CountryRepository extends MongoRepository<CountryEntity, String> {
}
