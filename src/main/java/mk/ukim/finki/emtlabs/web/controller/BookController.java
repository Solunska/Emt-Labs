package mk.ukim.finki.emtlabs.web.controller;

import mk.ukim.finki.emtlabs.model.Author;
import mk.ukim.finki.emtlabs.model.Book;
import mk.ukim.finki.emtlabs.model.dto.BookDto;
import mk.ukim.finki.emtlabs.model.enumerations.Category;
import mk.ukim.finki.emtlabs.service.AuthorService;
import mk.ukim.finki.emtlabs.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String getBookPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Book> books = this.bookService.findAll();
        model.addAttribute("books", books);
        model.addAttribute("bodyContent", "books");
        return "master-template";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        this.bookService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/edit-form/{id}")
    public String editBookPage(@PathVariable Long id, Model model) {
        if (this.bookService.findById(id).isPresent()) {
            Book book = this.bookService.findById(id).get();
            List<Category> categories = List.of(Category.values());
            List<Author> authors = this.authorService.findALl();

            model.addAttribute("categories", categories);
            model.addAttribute("authors", authors);
            model.addAttribute("book", book);
            model.addAttribute("bodyContent", "add-book");
            return "master-template";
        }
        return "redirect:/books?error=BookNotFound";
    }

    @GetMapping("/add-form")
    public String addBookPage(Model model) {
        List<Category> categories = List.of(Category.values());
        List<Author> authors = this.authorService.findALl();
        model.addAttribute("categories", categories);
        model.addAttribute("authors", authors);
        model.addAttribute("bodyContent", "add-book");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam(required = false) Long id,
                           @RequestParam String name,
                           @RequestParam Category category,
                           @RequestParam Long authorId,
                           @RequestParam Integer availableCopies) {
        if (id != null)
            this.bookService.edit(id, name, category, authorId, availableCopies);
        else
            this.bookService.save(name, category, authorId, availableCopies);

        return "redirect:/books";
    }

    @PostMapping("/mark-as-taken/{id}")
    public String markBookAsTaken(@PathVariable Long id) {
        if (this.bookService.findById(id).isPresent()) {
            Book book = this.bookService.findById(id).get();
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            this.bookService.save(book.getName(), book.getCategory(), book.getAuthor().getId(), book.getAvailableCopies());
        }
        return "redirect:/books";
    }
}
