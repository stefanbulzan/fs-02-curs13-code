package ro.fasttrackit.curs13.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("countries")
public record CountryEntity(
        String id,
        String name,
        String capital) {
}
