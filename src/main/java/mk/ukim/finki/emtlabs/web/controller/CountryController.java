package mk.ukim.finki.emtlabs.web.controller;

import mk.ukim.finki.emtlabs.model.Author;
import mk.ukim.finki.emtlabs.model.Book;
import mk.ukim.finki.emtlabs.model.Country;
import mk.ukim.finki.emtlabs.model.enumerations.Category;
import mk.ukim.finki.emtlabs.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public String getCountryPage(Model model) {
        model.addAttribute("countries", this.countryService.findALl());
        model.addAttribute("bodyContent", "countries");
        return "master-template";
    }

    @GetMapping("/add-form")
    public String addCountryPage(Model model) {
        model.addAttribute("bodyContent", "add-country");
        return "master-template";
    }
    @GetMapping("/edit-form/{id}")
    public String editCountryPage(@PathVariable Long id, Model model) {
        if (this.countryService.findById(id).isPresent()) {
            Country country = this.countryService.findById(id).get();

            model.addAttribute("country", country);
            model.addAttribute("bodyContent", "add-country");
            return "master-template";
        }
        return "redirect:/countries?error=CountryNotFound";
    }

    @PostMapping("/add")
    public String saveAuthor(@RequestParam(required = false) Long id,
                             @RequestParam String name,
                             @RequestParam String continent) {
        if (id != null)
            this.countryService.edit(id, name, continent);
        else
            this.countryService.save(name, continent);

        return "redirect:/countries";
    }
}
