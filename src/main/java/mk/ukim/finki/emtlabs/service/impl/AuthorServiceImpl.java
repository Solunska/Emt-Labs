package mk.ukim.finki.emtlabs.service.impl;

import mk.ukim.finki.emtlabs.model.Author;
import mk.ukim.finki.emtlabs.model.Country;
import mk.ukim.finki.emtlabs.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emtlabs.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.emtlabs.repository.AuthorRepository;
import mk.ukim.finki.emtlabs.repository.CountryRepository;
import mk.ukim.finki.emtlabs.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public List<Author> findALl() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> save(String name, String surname, Long countryId) {
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new CountryNotFoundException(countryId));
        return Optional.of(this.authorRepository.save(new Author(name, surname, country)));
    }

    @Override
    public Optional<Author> edit(Long id, String name, String surname, Long countryId) {
        Author author = this.authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        Country country = this.countryRepository.findById(countryId).orElseThrow(() -> new CountryNotFoundException(countryId));

        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        
        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
