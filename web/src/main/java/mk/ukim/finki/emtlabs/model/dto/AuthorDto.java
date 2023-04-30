package mk.ukim.finki.emtlabs.model.dto;

import lombok.Data;
import mk.ukim.finki.emtlabs.model.Country;

@Data
public class AuthorDto {

    private String name;

    private String surname;

    private Long country;

    public AuthorDto(String name, String surname, Long country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public AuthorDto() {
    }
}
