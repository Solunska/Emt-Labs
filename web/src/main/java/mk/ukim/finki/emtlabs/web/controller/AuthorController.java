package mk.ukim.finki.emtlabs.web.controller;

import mk.ukim.finki.emtlabs.model.Country;
import mk.ukim.finki.emtlabs.service.AuthorService;
import mk.ukim.finki.emtlabs.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final CountryService countryService;

    public AuthorController(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @GetMapping
    public String getAuthorPage(Model model) {
        model.addAttribute("authors", this.authorService.findALl());
        model.addAttribute("bodyContent", "authors");
        return "master-template";
    }

    @GetMapping("/add-form")
    public String addAuthorPage(Model model) {
        List<Country> countries = this.countryService.findALl();
        model.addAttribute("bodyContent", "add-author");
        model.addAttribute("countries", countries);
        return "master-template";
    }

    @PostMapping("/add")
    public String saveAuthor(@RequestParam(required = false) Long id,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam Long countryId) {
        if (id != null)
            this.authorService.edit(id, name, surname, countryId);
        else
            this.authorService.save(name, surname, countryId);

        return "redirect:/authors";
    }
}
