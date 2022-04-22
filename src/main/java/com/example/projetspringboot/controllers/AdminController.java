package com.example.projetspringboot.controllers;

import com.example.projetspringboot.entities.City;
import com.example.projetspringboot.entities.Library;
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

    AdminController(CityRepository cityrepo, LibraryRepository librepo) {
        this.cityrepo=cityrepo;
        this.librepo=librepo;
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
}
