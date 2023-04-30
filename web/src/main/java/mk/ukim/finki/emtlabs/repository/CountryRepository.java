package mk.ukim.finki.emtlabs.repository;

import mk.ukim.finki.emtlabs.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
