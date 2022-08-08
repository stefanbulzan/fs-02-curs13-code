package ro.fasttrackit.curs13;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "countries.api")
public record CountryApiConfig(String url) {

}
