package mk.ukim.finki.emtlabs.service;

import mk.ukim.finki.emtlabs.model.Author;
import mk.ukim.finki.emtlabs.model.Book;
import mk.ukim.finki.emtlabs.model.Country;
import mk.ukim.finki.emtlabs.model.dto.AuthorDto;
import mk.ukim.finki.emtlabs.model.dto.BookDto;
import mk.ukim.finki.emtlabs.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(Long id);

    List<Author> findALl();

    Optional<Author> save(String name, String surname, Long countryId);

    Optional<Author> edit(Long id, String name, String surname, Long countryId);

    void deleteById(Long id);

    Optional<Author> save(AuthorDto authorDto);

    Optional<Author> edit(Long id, AuthorDto authorDto);
}
