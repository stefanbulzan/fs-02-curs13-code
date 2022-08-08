package ro.fasttrackit.curs13.model;

import lombok.With;
import org.springframework.data.mongodb.core.mapping.Document;

@With
@Document("countries")
public record CountryEntity(
        String id,
        String name,
        String capital) {
}
