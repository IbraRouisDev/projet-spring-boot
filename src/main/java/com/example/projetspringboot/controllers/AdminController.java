package com.example.projetspringboot.controllers;

import com.example.projetspringboot.entities.Book;
import com.example.projetspringboot.entities.City;
import com.example.projetspringboot.entities.Library;
import com.example.projetspringboot.formData.BookToLibraryFormData;
import com.example.projetspringboot.repositories.BookRepository;
import com.example.projetspringboot.repositories.CityRepository;
import com.example.projetspringboot.repositories.LibraryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private CityRepository cityrepo;
    private LibraryRepository librepo;
    private BookRepository bookrepo;

    AdminController(CityRepository cityrepo, LibraryRepository librepo, BookRepository bookrepo) {
        this.cityrepo = cityrepo;
        this.librepo = librepo;
        this.bookrepo = bookrepo;
    }

    @GetMapping("")
    public String index() {
        return "admin/index";
    }

    @GetMapping("/city")
    public String cityForm(Model model) {
        model.addAttribute("city", new City());
        return "admin/city_form";
    }

    @PostMapping("/city")
    public String cityPosthandler(@ModelAttribute City city, Model model) {
        if (cityrepo.findByName(city.getName()) != null) {
            return "failed";
        } else {
            cityrepo.save(city);
            return cityForm(model);
        }
    }

    @GetMapping("/library")
    public String libraryForm(Model model) {
        model.addAttribute("cities", cityrepo.findAll());
        model.addAttribute("library", new Library());
        return "admin/library_form";
    }

    @PostMapping("/library")
    public String libraryPostHandler(@ModelAttribute Library library, Model model) {
        if (librepo.findByName(library.getName()) != null) {
            return "failed";
        } else {
            librepo.save(library);
            return libraryForm(model);
        }
    }

    @GetMapping("/book")
    public String bookForm(Model model) {
        model.addAttribute("book", new Book());
        return "admin/book_form";
    }

    @PostMapping("/book")
    public String bookPostHandler(@ModelAttribute Book book, Model model) {
        if (bookrepo.findByName(book.getName()) != null) {
            return "failed";
        } else {
            bookrepo.save(book);
            model.addAttribute("book", new Book());
            return "admin/book_form";
        }
    }

    @GetMapping("/booksmanager")
    public String booksmanager(@RequestParam(value = "search_type", defaultValue = "") String searchType, @RequestParam(value = "search_value", defaultValue = "") String searchValue, Model model) {
        if (searchType.equals("name")) {
            var books = bookrepo.findByNameContainingIgnoreCase(searchValue);
            model.addAttribute("books", books);
        } else if (searchType.equals("author")) {
            var books = bookrepo.findByAuthorContainingIgnoreCase(searchValue);
            model.addAttribute("books", books);
        } else if (searchType.equals("id")) {
            var book = bookrepo.findById(Long.parseLong(searchValue));
            if (book != null) {
                var books = new ArrayList<Book>();
                books.add(book);
                model.addAttribute("books", books);
            }
        }
        return "admin/books_manager";
    }

    @GetMapping("/book/addtolib")
    public String add_book_to_library(@RequestParam(value = "id") long id, Model model) {
        var libraries = librepo.findAll();
        var formData = new BookToLibraryFormData();
        formData.setBook_id(id);
        model.addAttribute("formData", formData);
        model.addAttribute("libraries", libraries);
        return "admin/book/add_to_library";
    }

    @PostMapping("/book/addtolib")
    public String add_book_to_library(@ModelAttribute BookToLibraryFormData formData) {
        var book = bookrepo.findById(formData.getBook_id());
        var library = librepo.findById(formData.getLibrary_id());
        library.getBooks().add(book);
        librepo.save(library);
        return "redirect:/admin/booksmanager";
    }

    @GetMapping("/book/removefromlib")
    public String remove_book_from_library(@RequestParam(value = "id") long id, Model model) {
        var book = bookrepo.findById(id);
        var libraries = book.getLibraries();
        var formData = new BookToLibraryFormData();
        formData.setBook_id(id);
        model.addAttribute("formData", formData);
        model.addAttribute("libraries", libraries);
        return "admin/book/remove_from_library";
    }

    @PostMapping("/book/removefromlib")
    public String remove_book_from_library(@ModelAttribute BookToLibraryFormData formData) {
        var book = bookrepo.findById(formData.getBook_id());
        var library = librepo.findById(formData.getLibrary_id());
        library.getBooks().remove(book);
        librepo.save(library);
        return "redirect:/admin/booksmanager";
    }
}
