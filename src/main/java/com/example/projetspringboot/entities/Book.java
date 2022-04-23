package com.example.projetspringboot.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String name;

    private String description;

    private String author;

    @Column(nullable = true)
    private String imgUrl;

    @ManyToMany(mappedBy = "books")
    private List<Library> libraries;

    public Book() {
    }

    public Book(String name, String description, String author) {
        this.name = name;
        this.description = description;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setLibraries(List<Library> libraries) {
        this.libraries = libraries;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
