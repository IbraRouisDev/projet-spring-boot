package com.example.projetspringboot.controllers;

import com.example.projetspringboot.entities.Book;
import com.example.projetspringboot.entities.City;
import com.example.projetspringboot.entities.Library;
import com.example.projetspringboot.repositories.BookRepository;
import com.example.projetspringboot.repositories.CityRepository;
import com.example.projetspringboot.repositories.LibraryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private CityRepository cityrepo;
    private LibraryRepository librepo;
    private BookRepository bookrepo;

    AdminController(CityRepository cityrepo, LibraryRepository librepo, BookRepository bookrepo) {
        this.cityrepo=cityrepo;
        this.librepo=librepo;
        this.bookrepo=bookrepo;
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
        if(cityrepo.findByName(city.getName()) != null) {
            return "failed";
        } else {
            cityrepo.save(city);
            model.addAttribute("city", new City());
            return "admin/city_form";
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
        if(librepo.findByName(library.getName()) != null) {
            return "failed";
        } else {
            librepo.save(library);
            model.addAttribute("library", new Library());
            return "admin/library_form";
        }
    }

    @GetMapping("/book")
    public String bookForm(Model model) {
        model.addAttribute("book", new Book());
        return "admin/book_form";
    }

    @PostMapping("/book")
    public String bookPostHandler(@ModelAttribute Book book, Model model) {
        if(bookrepo.findByName(book.getName()) != null) {
            return "failed";
        } else {
            bookrepo.save(book);
            model.addAttribute("book", new Book());
            return "admin/book_form";
        }
    }
}
