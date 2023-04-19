package mk.ukim.finki.emtlabs.repository;

import mk.ukim.finki.emtlabs.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBooksByName(String name);

    void deleteBookByName(String name);

    Page<Book> findAll(Pageable pageable);
}
