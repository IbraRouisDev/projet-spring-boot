package com.example.projetspringboot.controllers;

import com.example.projetspringboot.entities.Book;
import com.example.projetspringboot.repositories.BookRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {
    private BookRepository br;

    public BooksController(BookRepository br) {
        this.br = br;
    }

    @GetMapping("")
    public String index(@PageableDefault(size = 6) Pageable page, @RequestParam(value = "page", defaultValue = "0") int pageNumber, @RequestParam(value = "search", defaultValue = "") String searchValue, @RequestParam(value = "searchType", defaultValue = "") String searchType, Model model) {
        page.withPage(pageNumber);
        List<Book> books;
        if (searchType.equals("name")) {
            books = br.findByNameContainingIgnoreCase(searchValue, page);
        } else if (searchType.equals("author")) {
            books = br.findByAuthorContainingIgnoreCase(searchValue, page);
        } else {
            books = br.findAll(page).toList();
        }
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("searchType", searchType);
        model.addAttribute("page", pageNumber);
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/libraries/{id}")
    public String bookLibraries(@PathVariable long id, Model model) {
        var book = br.findById(id);
        var libraries = book.getLibraries();
        model.addAttribute("book", book);
        model.addAttribute("libraries", libraries);
        return "libraries";
    }
}
