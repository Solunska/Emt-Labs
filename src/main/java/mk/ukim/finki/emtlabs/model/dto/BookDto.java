package mk.ukim.finki.emtlabs.model.dto;

import lombok.Data;
import mk.ukim.finki.emtlabs.model.Author;
import mk.ukim.finki.emtlabs.model.enumerations.Category;

import javax.persistence.*;

@Data
public class BookDto {

    private String name;

    private Category category;

    private Long author;

    private Integer availableCopies;

    public BookDto(String name, Category category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public BookDto() {
    }
}
