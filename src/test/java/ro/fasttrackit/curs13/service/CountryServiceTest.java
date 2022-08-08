package ro.fasttrackit.curs13.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import ro.fasttrackit.curs13.model.CountryEntity;
import ro.fasttrackit.curs13.repository.CountryRepository;
import ro.fasttrackit.curs13.service.api.CountryApi;
import ro.fasttrackit.curs13.service.api.model.CountryApiModel;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CountryServiceTest {
    @Test
    @DisplayName("WHEN requesting a country THEN the capital comes form api, and id from repository")
    void test() {
        CountryApi countryApiMock = Mockito.mock(CountryApi.class);
        doReturn(Optional.of(new CountryApiModel("Hungary", "Budapest"))).when(countryApiMock).getCountryByName(eq("Hungary"));
        doReturn(Optional.of(new CountryApiModel("Romania", "Cluj"))).when(countryApiMock).getCountryByName(eq("Romania"));
//        doAnswer(invocationOnMock -> {
//            String argument = invocationOnMock.getArgument(0);
//            return Optional.of(new CountryApiModel("Russia", argument));
//        }).when(countryApiMock).getCountryByName(anyString());

        MyMockCountryRepository spy = spy(new MyMockCountryRepository());
        CountryService countryService = new CountryService(countryApiMock, spy);

        Optional<CountryEntity> result = countryService.addCountry(new CountryEntity(null, "Romania", null));

        ArgumentCaptor<String> countryNameCaptor = ArgumentCaptor.forClass(String.class);
        verify(countryApiMock, times(1)).getCountryByName(countryNameCaptor.capture());
        verify(spy, times(1)).save(any(CountryEntity.class));

        assertThat(countryNameCaptor.getValue()).isEqualTo("Romania");

        assertThat(result).isNotEmpty()
                .containsInstanceOf(CountryEntity.class)
                .contains(new CountryEntity("generatedId", "Romania", "Cluj"));
    }
}

class MyMockCountryRepository implements CountryRepository {

    @Override
    public CountryEntity save(CountryEntity entity) {
        return entity.withId("generatedId");
    }

    @Override
    public <S extends CountryEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<CountryEntity> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<CountryEntity> findAll() {
        return null;
    }

    @Override
    public Iterable<CountryEntity> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(CountryEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends CountryEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<CountryEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<CountryEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CountryEntity> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends CountryEntity> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends CountryEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends CountryEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends CountryEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends CountryEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CountryEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends CountryEntity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends CountryEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}