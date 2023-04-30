package mk.ukim.finki.emtlabs.web.rest;

import mk.ukim.finki.emtlabs.model.enumerations.Category;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/categories")
public class CategoryRestController {

    @GetMapping
    private List<Category> findAll() {
        return List.of(Category.values());
    }
}