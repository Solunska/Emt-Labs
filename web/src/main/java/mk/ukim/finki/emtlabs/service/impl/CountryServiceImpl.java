package mk.ukim.finki.emtlabs.service.impl;

import mk.ukim.finki.emtlabs.model.Country;
import mk.ukim.finki.emtlabs.model.dto.CountryDto;
import mk.ukim.finki.emtlabs.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.emtlabs.repository.CountryRepository;
import mk.ukim.finki.emtlabs.service.CountryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public List<Country> findALl() {
        return this.countryRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Country> save(String name, String continent) {
        return Optional.of(this.countryRepository.save(new Country(name, continent)));
    }

    @Override
    public Optional<Country> save(CountryDto countryDto) {
        return Optional.of(this.countryRepository.save(new Country(countryDto.getName(), countryDto.getContinent())));
    }


    @Override
    @Transactional
    public Optional<Country> edit(Long id, String name, String continent) {
        Country country = this.countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));

        country.setName(name);
        country.setContinent(continent);

        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public Optional<Country> edit(Long id, CountryDto countryDto) {
        Country country = this.countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));

        country.setName(countryDto.getName());
        country.setContinent(countryDto.getContinent());

        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }


}
