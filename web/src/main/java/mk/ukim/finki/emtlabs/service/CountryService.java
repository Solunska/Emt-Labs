package mk.ukim.finki.emtlabs.service;

import mk.ukim.finki.emtlabs.model.Author;
import mk.ukim.finki.emtlabs.model.Country;
import mk.ukim.finki.emtlabs.model.dto.AuthorDto;
import mk.ukim.finki.emtlabs.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    Optional<Country> findById(Long id);

    List<Country> findALl();

    Optional<Country> save(String name, String continent);

    Optional<Country> edit(Long id, String name, String continent);

    void deleteById(Long id);
    Optional<Country> save(CountryDto countryDto);

    Optional<Country> edit(Long id, CountryDto countryDto);
}
