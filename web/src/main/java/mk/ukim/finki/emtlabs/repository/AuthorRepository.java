package mk.ukim.finki.emtlabs.repository;

import mk.ukim.finki.emtlabs.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
